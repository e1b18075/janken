package oit.is.z1439.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Sample3AuthConfiguration
 */
@Configuration
@EnableWebSecurity
public class Lec03AuthConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * 誰がログインできるか(認証処理)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    // $ sshrun htpasswd -nbBC 10 user1 pAssw0rd
    auth.inMemoryAuthentication().withUser("user1")
        .password("$2y$10$3fXLs5lUlNUTXqa4kcnuguHrI2m2LwdPc5WtXvG4aPx.R9uyqtvuG").roles("USER");
    auth.inMemoryAuthentication().withUser("user2")
        .password("$2y$10$k7oaq61hj344mR.vDWUe6eXGbqQiexK0vJtAJTrqUXX4EFGizYORy").roles("USER");
    auth.inMemoryAuthentication().withUser("ふくなが")
        .password("$2y$10$0nTce6tUMMhBDBa1JU6N..JhZNONPAGtDBBAtAkG8hmKoqRoNVuhe").roles("USER");
    auth.inMemoryAuthentication().withUser("いがき")
        .password("$2y$10$gtZV7lJZY4oA22fqsLLyj.GhUXjgCKFR0NCrSFujll2ADbSkVba9W").roles("USER");

    // 開発中は↓の書き方でも良いが，平文でパスワードが保存される
    // auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("janken")).roles("USER");
    // auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder().encode("password")).roles("USER");
    // auth.inMemoryAuthentication().withUser("ふくなが").password(passwordEncoder().encode("janken")).roles("USER");
    // auth.inMemoryAuthentication().withUser("いがき").password(passwordEncoder().encode("igaki")).roles("USER");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 認証されたユーザがどこにアクセスできるか（認可処理）
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    // http://localhost:8000/sample3 で始まるURLへのアクセスはログインが必要
    // antMatchers().authenticated がantMatchersへのアクセスに認証を行うことを示す
    // antMatchers()の他にanyRequest()と書くとあらゆるアクセス先を表現できる
    // authenticated()の代わりにpermitAll()と書くと認証処理が不要であることを示す
    http.authorizeRequests().antMatchers("/lec02/**").authenticated();
    /**
     * 以下2行はh2-consoleを利用するための設定なので，開発が完了したらコメントアウトすることが望ましい
     * CSRFがONになっているとフォームが対応していないためアクセスできない
     * HTTPヘッダのX-Frame-OptionsがDENYになるとiframeでlocalhostでのアプリが使えなくなるので，H2DBのWebクライアントのためだけにdisableにする必要がある
     */
    http.csrf().disable();
    http.headers().frameOptions().disable();

    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8080/ に戻る
    http.logout().logoutSuccessUrl("/");
  }
}
