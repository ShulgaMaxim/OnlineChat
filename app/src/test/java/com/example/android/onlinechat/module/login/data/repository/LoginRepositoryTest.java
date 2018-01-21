package com.example.android.onlinechat.module.login.data.repository;

import com.example.android.onlinechat.module.login.data.dataSource.FirebaseAuthDataSource;
import com.example.android.onlinechat.module.login.data.model.UserDto;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author mshulga
 * @since 21.01.18
 */

public class LoginRepositoryTest {

    private FirebaseAuthDataSource mFirebaseAuthDataSource;
    private LoginRepository mLoginRepository;

    @Before
    public void beforeTest() {
        mFirebaseAuthDataSource = mock(FirebaseAuthDataSource.class);
        mLoginRepository = new LoginRepositoryImpl(mFirebaseAuthDataSource);
    }

    @Test
    public void testLoginWithCurrentUser() {
        String uuid = "uuid";
        UserDto currentUser = new UserDto(uuid);
        when(mFirebaseAuthDataSource.getCurrentUser()).thenReturn(Maybe.just(currentUser));
        when(mFirebaseAuthDataSource.signNewUser()).thenReturn(Single.error(new Exception()));

        TestObserver<UserDto> testObserver = new TestObserver<>();
        mLoginRepository.login().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        UserDto userDto = testObserver.values().get(0);
        Assert.assertEquals(currentUser, userDto);
    }

    @Test
    public void testSignNewUser() {
        String uuid = "uuid";
        UserDto currentUser = new UserDto(uuid);
        when(mFirebaseAuthDataSource.getCurrentUser()).thenReturn(Maybe.empty());
        when(mFirebaseAuthDataSource.signNewUser()).thenReturn(Single.just(currentUser));

        TestObserver<UserDto> testObserver = new TestObserver<>();
        mLoginRepository.login().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        UserDto userDto = testObserver.values().get(0);
        Assert.assertEquals(currentUser, userDto);
    }

    @Test
    public void testSignNewUserFailed() {
        Exception exception = new Exception();
        when(mFirebaseAuthDataSource.getCurrentUser()).thenReturn(Maybe.empty());
        when(mFirebaseAuthDataSource.signNewUser()).thenReturn(Single.error(exception));

        TestObserver<UserDto> testObserver = new TestObserver<>();
        mLoginRepository.login().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertError(exception);
    }
}
