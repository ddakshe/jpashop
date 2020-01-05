package kennen.jpashop.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Slf4j
@Configuration
@Profile("tcp")
public class H2ServerConfiguration {


    /**
     * @return
     * @throws SQLException
     * @see org.h2.server.TcpServer
     */
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource() throws SQLException {
        //Server server = adviceRun(9093, "external_db_name", "dbname", FilePath.absolute);
        Server server = defaultRun(9093);
        if (server.isRunning(true)) {
            log.info("server run success");
        }
        log.info("h2 server url = {}", server.getURL());

        return new HikariDataSource();
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