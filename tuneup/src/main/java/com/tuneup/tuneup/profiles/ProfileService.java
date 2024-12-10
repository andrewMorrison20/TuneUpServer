package com.tuneup.tuneup.profiles;

import com.tuneup.tuneup.genres.GenreMapper;
import com.tuneup.tuneup.pricing.Price;
import com.tuneup.tuneup.pricing.PriceMapper;
import com.tuneup.tuneup.profiles.dtos.ProfileDto;
import com.tuneup.tuneup.profiles.repositories.ProfileRepository;

import com.tuneup.tuneup.users.model.AppUser;
import com.tuneup.tuneup.users.services.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final ProfileValidator profileValidator;
    private final AppUserService appUserService;
    private final PriceMapper priceMapper;
    private final GenreMapper genreMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper, ProfileValidator profileValidator, AppUserService appUserService, PriceMapper priceMapper, GenreMapper genreMapper) {
        this.appUserService = appUserService;
        this.profileMapper = profileMapper;
        this.profileRepository = profileRepository;
        this.profileValidator = profileValidator;
        this.priceMapper = priceMapper;
        this.genreMapper = genreMapper;
    }


    /**
     * Get Page of profiles from db
     * @param pageable
     * @return
     */
    public Page<ProfileDto> findProfilesDto(Pageable pageable) {

           Page<Profile> profiles = profileRepository.findAll(pageable);
           return profiles.map(profileMapper::toProfileDto);
    }

    /**
     * Creates a new profile based on the info passed in the dto.
     * @param profileDto details of profile to create
     * @return dto of successful profile creation. Else throw validation/server exception
     */
    @Transactional
    public ProfileDto createProfile(ProfileDto profileDto) {
        profileValidator.validatorProfileDto(profileDto);
        AppUser appUser = appUserService.findById(profileDto.getAppUserId());
        Profile profile = profileMapper.toProfile(profileDto);
        profile.setAppUser(appUser);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toProfileDto(savedProfile);
    }


    public ProfileDto getProfileDto(Long id) {
            return profileRepository.findById(id)
                    .map(profileMapper::toProfileDto)
                    .orElse(null);
        }

    @Transactional
    public ProfileDto updateProfile(ProfileDto profileDto) {
        Profile existingProfile = profileRepository.findById(profileDto.getId())
                .orElseThrow();
        //TO-DO extend this either using beansUtils or Mapper and custom logic to cover all fields of profile
        //Need to think about fetching from db as oppossed to front end always sending names with sub dtos.


        if(profileDto.getPrices() !=null) {
            Set<Price> updatedPrices = profileDto.getPrices()
                    .stream()
                    .map(priceMapper::toPrice)
                    .collect(Collectors.toSet());
            existingProfile.setPrices(updatedPrices);
        }

        if(profileDto.getGenres()!=null){
            existingProfile.setGenres(
                    profileDto.getGenres().stream()
                            .map(genreMapper:: toGenre)
                            .collect(Collectors.toSet())
            );
        }

        Profile profile = profileRepository.save(existingProfile);

        return profileMapper.toProfileDto(profile);
    }
}





