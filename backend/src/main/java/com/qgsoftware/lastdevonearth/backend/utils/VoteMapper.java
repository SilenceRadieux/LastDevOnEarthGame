package com.qgsoftware.lastdevonearth.backend.utils;

import com.qgsoftware.lastdevonearth.backend.dto.VoteDTO;
import com.qgsoftware.lastdevonearth.backend.entities.VoteEntity;
import com.qgsoftware.lastdevonearth.backend.services.VoteServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    VoteServiceModel voteDTOToVoteServiceModel(VoteDTO voteDTO);

    VoteEntity voteServiceModelToVoteEntity(VoteServiceModel voteServiceModel);

    List<VoteServiceModel> listVoteEntityToListVoteServiceModel(List<VoteEntity> listVoteEntity);

    List<VoteDTO> listVoteServiceModelToVoteDTO(List<VoteServiceModel> voteServiceModel);

    VoteServiceModel voteEntityToVoteServiceModel(VoteEntity voteEntity);

    VoteDTO voteServiceModelToVoteDTO(VoteServiceModel voteServiceModel);

}
