package com.qgsoftware.lastdevonearth.backend.controllers;

import com.qgsoftware.lastdevonearth.backend.dto.VoteDTO;
import com.qgsoftware.lastdevonearth.backend.services.VoteService;
import com.qgsoftware.lastdevonearth.backend.utils.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vote")
public class VoteController {

    private final VoteService voteService;
    VoteMapper voteMapper = VoteMapper.INSTANCE;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    @GetMapping
    public List<VoteDTO> findAll() {
        return voteMapper.listVoteServiceModelToVoteDTO(voteService.findAll());
    }

    @GetMapping("{id}")
    public VoteDTO findById(@PathVariable("id") Long id) {
        return voteMapper.voteServiceModelToVoteDTO(voteService.findById(id));
    }

    @PutMapping("{id}")
    public void updateVote(@PathVariable("id") Long id, @RequestBody VoteDTO voteDTO) {
        voteService.add(voteMapper.voteDTOToVoteServiceModel(voteDTO), id);
    }

    @DeleteMapping("{id}")
    public boolean deleteVote(@PathVariable("id") Long id) {
        return voteService.delete(id);
    }

}
