package com.lyes.moviecatalogservice.resources;

import com.lyes.moviecatalogservice.models.CatalogItem;
import com.lyes.moviecatalogservice.models.Movie;
import com.lyes.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID){

        RestTemplate restTemplate = new RestTemplate();

        // get all rated movie Id's
        List<Rating> allRatings = Arrays.asList(

                new Rating("1235", 5),
                new Rating("4874", 6)
        );
                return allRatings.stream().map(rating -> {
                    Movie movie  = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "adventure movie",rating.getRating());
                }).collect(Collectors.toList());
        // for each movie id get movie info

        // put them all together
        /*return Collections.singletonList(
                new CatalogItem("Transformers", "adventure movie",4)
        );*/
    }
}
