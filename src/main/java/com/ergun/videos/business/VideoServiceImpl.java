package com.ergun.videos.business;

import com.ergun.videos.dataAccess.VideoRepository;
import com.ergun.videos.entities.Video;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService{
    private VideoRepository repository;
    @Override
    public List<Video> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(Video video) {
        repository.save(video);
    }

    @Override
    public void delete(int id) {
        Video video = repository.findById(id).orElseThrow();
        repository.delete(video);
    }

    @Override
    public Video getById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Video> getByTitle(String name) {
        return repository.findByTitleContainingIgnoreCase(name);
    }
}
