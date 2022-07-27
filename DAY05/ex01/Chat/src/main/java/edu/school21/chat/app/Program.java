package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Program program = new Program();
        program.mss();
    }

    private HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource dataSource = null;
        try {
            config.setJdbcUrl( "jdbc:postgresql://localhost:5432/chat" );
            config.setUsername( "postgres" );
            config.setPassword( "123" );
            dataSource = new HikariDataSource(config);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private void mss() {
        HikariDataSource dataSource = hikariDataSource();
        Scanner sc = new Scanner(System.in);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        System.out.println("Введите id сообщения или литеру для продолжения");
        while (true) {
            System.out.print("->");
            if (sc.hasNextInt()) {
                Long messageId = sc.nextLong();
                Optional<Message> optional = messagesRepository.findById(messageId);
                if (optional.isPresent()) {
                    Message message = optional.get();
                    System.out.println(message);
                } else {
                    System.out.println("Сообщение не найдено!");
                }
            } else {
                break;
            }
        }
        dataSource.close();
    }

}
