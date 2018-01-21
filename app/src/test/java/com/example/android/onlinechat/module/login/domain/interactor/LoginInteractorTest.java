package com.example.android.onlinechat.module.login.domain.interactor;

import com.example.android.onlinechat.module.login.data.model.UserDto;
import com.example.android.onlinechat.module.login.data.repository.LoginRepository;
import com.example.android.onlinechat.module.login.domain.model.UserEntity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author mshulga
 * @since 21.01.18
 */

public class LoginInteractorTest {

    private LoginRepository mLoginRepository;
    private LoginInteractor mLoginInteractor;

    @Before
    public void beforeTest() {
        mLoginRepository = mock(LoginRepository.class);
        mLoginInteractor = new LoginInteractorImpl(mLoginRepository);
    }

    @Test
    public void testAuthWithNickname() {
        String nickname = "Volodya";
        String uuid = "uuid";

        when(mLoginRepository.login()).thenReturn(Single.just(new UserDto(uuid)));

        TestObserver<UserEntity> testObserver = new TestObserver<>();
        mLoginInteractor.login(nickname).subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        testObserver.assertValueCount(1);

        UserEntity userEntity = testObserver.values().get(0);
        Assert.assertEquals(nickname, userEntity.getNickName());
        Assert.assertEquals(uuid, userEntity.getUid());
    }

    @Test
    public void testAuthWithNicknameFailed() {
        String nickname = "Volodya";

        Exception exception = new Exception();
        when(mLoginRepository.login()).thenReturn(Single.error(exception));

        TestObserver<UserEntity> testObserver = new TestObserver<>();
        mLoginInteractor.login(nickname).subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertError(exception);
    }
}
