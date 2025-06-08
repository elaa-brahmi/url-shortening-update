package backend.example.demo.service;

import backend.example.demo.exception.NotFoundUrl;
import backend.example.demo.exception.TooLongUrl;
import backend.example.demo.exception.UrlExist;
import backend.example.demo.exception.TooManyAttempts;
import backend.example.demo.model.UrlShortened;
import backend.example.demo.model.User;
import backend.example.demo.repository.UrlRepository;
import backend.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;
    public String generateRandomUrl(){
        Random random=new Random();
        String shorturl;
        int counter=0;
        do{
            StringBuilder url=new StringBuilder();
            for(int i=0;i<SHORT_CODE_LENGTH;i++){
                url.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            shorturl=url.toString();
            counter++;

            if(counter>6){
                throw new TooManyAttempts("failed to generate unique code after 6 attempts");
            }


        }while(urlRepository.findByShortenedUrl(shorturl).isPresent());
        return "TrimUrl/"+shorturl;

    }
    public void generateCustomShortenedUrl(String shortenedUrl,String originalUrl,String userId){
        if(originalUrl.length()>255){
            throw new TooLongUrl("this url is too long try with a shorter one!");
        }
        if(urlRepository.findByShortenedUrl(shortenedUrl).isPresent() || urlRepository.findByOriginalUrl(originalUrl).isPresent()){
            throw new UrlExist("this url already exist, choose another");
        }
        Optional<User> user=userRepository.findById(Integer.parseInt(userId));
        if(user.isPresent()){
            User currentUser=user.get();
            UrlShortened newUrl = UrlShortened.builder()
                    .shortenedUrl("TrimUrl/"+shortenedUrl)
                    .originalUrl(originalUrl)
                    .accessCount(0)
                    .user(currentUser)
                    .createdAt(LocalDateTime.now())
                    .type("custom")
                    .build();
            urlRepository.save(newUrl);

        }


    }
    public void createUrl(String  Originalurl) throws TooManyAttempts {
        if(Originalurl.length()>255){
            throw new TooLongUrl("this url is too long try with a shorter one!");
        }
        if(urlRepository.findByOriginalUrl(Originalurl).isPresent()){
            throw new UrlExist("this url already exist, choose another");
        }
            String shorturl=generateRandomUrl();

            UrlShortened newUrl = UrlShortened.builder()
                    .shortenedUrl(shorturl)
                    .originalUrl(Originalurl)
                    .accessCount(0)
                    .type("generated")
                    .createdAt(LocalDateTime.now())
                    .build();
            urlRepository.save(newUrl);
    }
    public void copyUrl(Integer id){
        UrlShortened url=urlRepository.findById(id).get();
        url.setAccessCount(url.getAccessCount()+1);
        urlRepository.save(url);


    }
    public UrlShortened getUrlByShortenedUrl(String shortenedUrl){
        Optional<UrlShortened> url=urlRepository.findByShortenedUrl(shortenedUrl);
        return url.orElse(null);
    }
    public Optional<UrlShortened> getUrlFromShortenedUrl(String shortenedUrl){
       Optional<UrlShortened> url =urlRepository.findByShortenedUrlContainingIgnoreCase(shortenedUrl);
         if(url.isPresent()){
             UrlShortened urlShortened=url.get();
             urlRepository.save(urlShortened);
         }
         else{
             throw new NotFoundUrl("Url not found for shortened url: "+shortenedUrl);
         }
         return url;
    }

    public UrlShortened updateShortUrl(String originalUrl, Integer urlId){
        Optional<UrlShortened> original =urlRepository.findById(urlId);
        if(original.isPresent()){

            original.get().setOriginalUrl(originalUrl);
            original.get().setUpdatedAt(LocalDateTime.now());
            return urlRepository.save(original.get());
        }
        return null;

    }
    public Optional<List<UrlShortened>> getAllUrls(){
        List<UrlShortened> urls=urlRepository.findAllOrderByUpdatedOrCreatedDesc();
        if(urls.isEmpty()){
            throw new NotFoundUrl("no urls found");
        }
        else{
            return Optional.of(urls);
        }

    }
    public void deleteUrl(Integer id){
        urlRepository.deleteById(id);

    }
    public Integer getNumberAccessed(Integer id){
        UrlShortened url=urlRepository.findById(id).get();
        return url.getAccessCount();

    }

}
