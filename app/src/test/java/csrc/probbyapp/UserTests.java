package csrc.probbyapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import csrc.probbyapp.database.UserDataHandler;

public class UserTests {

    @Mock
    private FirebaseFirestore mockDb;
    @Mock
    private CollectionReference mockCollectionReference;
    @Mock
    private DocumentReference mockDocumentReference;
    @Mock
    private Task<Void> mockVoidTask;

    private UserDataHandler userDataHandler;
    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        userDataHandler = new UserDataHandler(mockDb);
        
        // Inject the mock DB
        userDataHandler.db = mockDb;

        when(mockDb.collection(anyString())).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document(anyString())).thenReturn(mockDocumentReference);
        when(mockDocumentReference.set(any())).thenReturn(mockVoidTask);
    }

    @After
    public void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Test
    public void testAddUser_CallsFirestore() {
        String uid = "test_uid";
        String name = "Test User";
        String email = "test@example.com";

        userDataHandler.addUser(uid, name, email);

        verify(mockDb).collection("users");
        verify(mockCollectionReference).document(uid);
        verify(mockDocumentReference).set(anyMap());
    }

    @Test
    public void testGetUser_CallsFirestore() {
        String uid = "test_uid";

        userDataHandler.getUser(uid);

        verify(mockDb).collection("users");
        verify(mockCollectionReference).document(uid);
        verify(mockDocumentReference).get();
    }
}
