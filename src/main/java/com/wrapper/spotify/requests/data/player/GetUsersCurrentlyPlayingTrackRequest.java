package com.wrapper.spotify.requests.data.player;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.CurrentlyPlayingTrack;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class GetUsersCurrentlyPlayingTrackRequest extends AbstractRequest {

  private GetUsersCurrentlyPlayingTrackRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public CurrentlyPlayingTrack get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new CurrentlyPlayingTrack.JsonUtil().createModelObject(getJson());
  }

  public SettableFuture<CurrentlyPlayingTrack> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return getAsync(new CurrentlyPlayingTrack.JsonUtil().createModelObject(getJson()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    @Override
    public GetUsersCurrentlyPlayingTrackRequest build() {
      return new GetUsersCurrentlyPlayingTrackRequest(this);
    }

  }
}
