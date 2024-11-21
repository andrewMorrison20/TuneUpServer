package com.tuneup.tuneup;

import com.tuneup.tuneup.profiles.repositories.ProfileRepository;
import com.tuneup.tuneup.users.exceptions.ValidationException;
import com.tuneup.tuneup.users.repository.AppUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    private final ProfileRepository profileRepository;
    private final AppUserRepository appUserRepository;

    public ReviewValidator(ProfileRepository profileRepository, AppUserRepository appUserRepository) {
        this.profileRepository = profileRepository;
        this.appUserRepository = appUserRepository;
    }

    public void  validateReviewDto(ReviewDto reviewDto){
        validateReviewProfile(reviewDto.getProfileId());
        validateReview(reviewDto.getReviewerUserID());

    }

    private void validateReviewProfile(long profileId) {
         if(profileRepository.existsById(profileId)){
             return;
         } else {
             throw new ValidationException("Profile does not exist");
         }
    }

    private void validateReview(Long reviewerId) {
        if(appUserRepository.existsById(reviewerId)){
            return;
        } else {
            throw new ValidationException(reviewerId + " is not a registered user");
        }
    }
}
