package com.tuneup.tuneup.profiles.dtos;

import com.tuneup.tuneup.Instruments.InstrumentDto;
import com.tuneup.tuneup.genres.GenreDto;
import com.tuneup.tuneup.images.ImageDto;
import com.tuneup.tuneup.pricing.PriceDto;
import com.tuneup.tuneup.profiles.ProfileType;
import com.tuneup.tuneup.regions.RegionDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProfileDto {

    private long id;
    private String displayName;
    private String bio;
    private boolean onlineLessons;
    private ProfileType profileType;
    private Set<InstrumentDto> instruments;
    private long appUserId;
    private Set<PriceDto> prices;
    private Set<GenreDto> genres;
    private ImageDto profilePicture;

    public ImageDto getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ImageDto profilePicture) {
        this.profilePicture = profilePicture;
    }

    public RegionDto getTuitionRegion() {
        return tuitionRegion;
    }

    public void setTuitionRegion(RegionDto tuitionRegion) {
        this.tuitionRegion = tuitionRegion;
    }

    private RegionDto tuitionRegion;

    public void setId(long id) {
        this.id = id;
    }

    public Set<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDto> genres) {
        this.genres = genres;
    }

    public Set<PriceDto> getPrices() {
        return prices;
    }
    public void setPrices(Set<PriceDto> prices) {
        this.prices = prices;
    }

    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isOnlineLessons() {
        return onlineLessons;
    }

    public void setOnlineLessons(boolean onlineLessons) {
        this.onlineLessons = onlineLessons;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public Set<InstrumentDto> getInstruments() {
        return instruments;
    }

    public void setInstruments(Set<InstrumentDto> instruments) {
        this.instruments = instruments;
    }

    public long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(long appUserId) {
        this.appUserId = appUserId;
    }

}