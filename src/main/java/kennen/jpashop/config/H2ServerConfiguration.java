package kennen.jpashop.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
public class H2ServerConfiguration {


    @Bean
    public DataSource dataSource(){
        try {
            Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9093").start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("1234");
        dataSource.setJdbcUrl(
                "jdbc:h2:mem:jpashop;INIT=CREATE SCHEMA IF NOT EXISTS jobflex;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        return dataSource;
    }

    private Server adviceRun(int port, String externalDbName, String dbname, FilePath db_store) throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", port + "", "-key", externalDbName, db_store.value2(dbname)).start();
    }

    private Server defaultRun(int port) throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", port + "").start();
    }

    enum FilePath {
        absolute("~/"),
        relative("./");
        String prefix;

        FilePath(String prefix) {
            this.prefix = prefix;
        }

        public String value2(String dbname) {
            return prefix + dbname;
        }
    }
}