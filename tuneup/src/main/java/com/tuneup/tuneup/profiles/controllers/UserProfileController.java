package com.tuneup.tuneup.profiles.controllers;


import com.tuneup.tuneup.pricing.PriceDto;
import com.tuneup.tuneup.profiles.ProfileService;
import com.tuneup.tuneup.profiles.dtos.ProfileDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    private final ProfileService profileService;

    public UserProfileController(final ProfileService profileService) {

        this.profileService = profileService;
    }

    @GetMapping()
    public Page<ProfileDto> getAllProfiles(Pageable page) {
        return profileService.findProfilesDto(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
        ProfileDto profile = profileService.getProfileDto(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> getProfileByAppUserId(@PathVariable Long id) {
        ProfileDto profile = profileService.getProfileDtoByUserId(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto createdProfile = profileService.createProfile(profileDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto updatedProfile = profileService.updateProfile(profileDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    /*@PutMapping("/update/pricing/{id}")
    public ResponseEntity<ProfileDto> updatePricing(@RequestBody Set<PriceDto> priceDtoSet) {
        updatedProfile
    }*/
}
