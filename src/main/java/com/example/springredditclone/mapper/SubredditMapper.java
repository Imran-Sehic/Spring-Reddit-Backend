package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.springredditclone.model.Subreddit;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()")
    SubredditDto mapSubredditToDto(Subreddit subreddit);
    
    default Integer mapPosts(List<Post> numberOfPosts){
        return numberOfPosts.size();
    }
    
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)        
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
    
}
