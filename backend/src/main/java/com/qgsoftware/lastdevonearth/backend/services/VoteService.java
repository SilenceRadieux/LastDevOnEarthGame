package com.qgsoftware.lastdevonearth.backend.services;

import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;
import com.qgsoftware.lastdevonearth.backend.repositories.VoteRepository;
import com.qgsoftware.lastdevonearth.backend.utils.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    VoteMapper voteMapper = VoteMapper.INSTANCE;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void add(VoteServiceModel voteServiceModel, @Nullable Long id) {
        VoteEntity voteEntity = voteMapper.voteServiceModelToVoteEntity(voteServiceModel);
        if (id != null) {
            voteEntity.setId(id);
        }
        voteRepository.save(voteEntity);
    }

    public List<VoteServiceModel> findAll() {
        return voteMapper.listVoteEntityToListVoteServiceModel((List<VoteEntity>) voteRepository.findAll());
    }


    public VoteServiceModel findById(Long id) {
        Optional<VoteEntity> voteEntity = voteRepository.findById(id);
        if (voteEntity.isPresent()) {
            return voteMapper.voteEntityToVoteServiceModel(voteEntity.get());
        }
        return null;
    }

    public boolean delete(long id) {
        try {
            voteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
