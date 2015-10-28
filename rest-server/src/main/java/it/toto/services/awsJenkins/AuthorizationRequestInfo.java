package it.toto.services.awsJenkins;

import it.toto.services.awsJenkins.request.AuthorizationRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by toto on 11/12/14.
 */
@RequiredArgsConstructor
public class AuthorizationRequestInfo {

    @NonNull
    @Getter
    AuthorizationRequest authorizationRequest;
    @NonNull
    @Getter
    AuthenticationType authenticationType;
}