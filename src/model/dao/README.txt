***추후에 DB를 사용할 때 아래와 같이 사용.***

***카드Dao에서 쓴다고 가정***
public class CardDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    ***게시물 쓰기 함수(예시)***
    public void write() {
        try {
              conn = DBDao.getConnection();
              ***이후 SQL 실행 로직***
            // ... SQL 실행 로직 ...
        } catch(Exception e) {
        }
    }
}

<민서님>


<태형님>
UserDao

<병모님>
DBDao
GameLogDao
SaveDao
