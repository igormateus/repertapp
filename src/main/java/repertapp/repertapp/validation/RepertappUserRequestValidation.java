package repertapp.repertapp.validation;

import javax.validation.Valid;

import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.mapper.RepertappUserMapper;
import repertapp.repertapp.repository.RepertappUserRepository;
import repertapp.repertapp.request.RepertappUserPostRequestBody;
import repertapp.repertapp.request.RepertappUserPutRequestBody;

public class RepertappUserRequestValidation {

    private static RepertappUserRepository userRepository;

    public static RepertappUser valideAdd(@Valid RepertappUserPostRequestBody userRequest, RepertappUserRepository repository) {
        userRepository = repository;

        checkRepertappUserNameUnique(userRequest.getName());

        checkRepertappUserEmailUnique(userRequest.getEmail());

        checkRepertappUserUsernameUnique(userRequest.getUsername());

        return RepertappUserMapper.INSTANCE.toRepertappUser(userRequest);
    }

    public static void valideUpdate(@Valid RepertappUserPutRequestBody userRequest, RepertappUser user,
            RepertappUserRepository repository) {
        userRepository = repository;

        if (!(user.getName().equals(userRequest.getName())))
            checkRepertappUserNameUnique(userRequest.getName());

        if (!(user.getEmail().equals(userRequest.getEmail())))
            checkRepertappUserEmailUnique(userRequest.getEmail());

        if (!(user.getUsername().equals(userRequest.getUsername())))
            checkRepertappUserUsernameUnique(userRequest.getUsername());
    }

    private static void checkRepertappUserUsernameUnique(String username) {
        if (userRepository.existsByUsername(username))
            throw new ResourceAlreadyExists("User", "username", username);
    }

    private static void checkRepertappUserEmailUnique(String email) {
        if (userRepository.existsByEmail(email))
            throw new ResourceAlreadyExists("User", "email", email);

    }

    private static void checkRepertappUserNameUnique(String name) {
        if (userRepository.existsByName(name))
            throw new ResourceAlreadyExists("User", "name", name);
    }
    
}
