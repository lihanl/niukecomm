package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
//
//    @Test
//    public void testInsertUser() {
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("123456");
//        user.setSalt("abc");
//        user.setEmail("test@qq.com");
//        user.setHeaderUrl("http://www.nowcoder.com/101/png");
//        user.setCreateTime(new Date());
//        int rows = userMapper.insertUser(user);
//        System.out.println(rows);
//        System.out.println(user.getId());
//    }
//
//    @Test
//    public void updateUser() {
//        int rows = userMapper.updateStatus(159,1);
//        System.out.println(rows);
//        rows = userMapper.updateHeader(159,"http://nowcoder.com/102/png");
//        System.out.println(rows);
//        rows = userMapper.updatePassword(159, "ruarua");
//        System.out.println(rows);
//    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost discussPost: list) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicket.setTicket("abcd");
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abcd");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abcd", 1);
        loginTicket = loginTicketMapper.selectByTicket("abcd");
        System.out.println(loginTicket);
    }
    @Test
    public void insertDiscussPost() {
        DiscussPost discussPost = new DiscussPost();
        discussPost.setTitle("asasasa");
        discussPost.setContent("sadafhasjvcxhzjvchx");
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(1);
        discussPost.setUserId(123456);
        discussPost.setScore(12);
        discussPostMapper.insertDiscussPost(discussPost);
    }

    @Test
    public void testSelectLetters(){
        List<Message> list = messageMapper.selectConversations(111, 0, 20);
        for (Message m: list) {
            System.out.println(m);
        }
        System.out.println(messageMapper.selectConversationCount(111));

        list = messageMapper.selectLetters("111_112", 0, 10);
        for (Message m: list) {
            System.out.println(m);
        }

        System.out.println(messageMapper.selectLetterCount("111_112"));

        int count = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(count);


    }
}
