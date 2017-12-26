package com.wrapper.spotify.requests.data.playlists;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;

public class ReplaceTracksInPlaylistRequestTest {
  @Test
  public void shouldReplaceTracksInPlaylist_async() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = {"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:track:0BG2iE6McPhmAEKIhfqy1X"};

    final ReplaceTracksInPlaylistRequest request = api.replaceTracksInPlaylist(myUsername, myPlaylistId, tracksToAdd)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<Boolean> addTrackFuture = request.getAsync();

    Futures.addCallback(addTrackFuture, new FutureCallback<Boolean>() {
      @Override
      public void onSuccess(Boolean response) {
        assertFalse(response);
        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future: " + throwable.getMessage());
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldReplaceTracksInPlaylist_sync() throws Exception {
    final String accessToken = "someAccessToken";

    final Api api = Api.builder().accessToken(accessToken).build();

    final String myUsername = "thelinmichael";
    final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
    final String[] tracksToAdd = {"spotify:track:4BYGxv4rxSNcTgT3DsFB9o", "spotify:track:0BG2iE6McPhmAEKIhfqy1X"};

    final ReplaceTracksInPlaylistRequest request = api.replaceTracksInPlaylist(myUsername, myPlaylistId, tracksToAdd)
            .setHttpManager(TestUtil.MockedHttpManager.returningString(""))
            .build();

    assertFalse(request.get());
  }
}
