package com.yuan.utr.ws.util.email.template;

/**
 * Created by V644593 on 8/16/2016.
 */
public class JoinLeagueEmail extends SiteEmail
{
    public JoinLeagueEmail(String emailAddr, String name)
    {
        this.setToAddr(emailAddr);
        this.setSubject("Welcome to UTR League");
        String actualContent = emailTemplate.replace("[name]", name);
        this.setHtmlContent(actualContent);
    }

    private static String emailTemplate = "Hi [name], <br><br>" +
            "Welcome to UTR League. <br><br>" +
            "Congratulations! You have just joined UTR league. <br><br>" +
            "Enjoy playing matches in the league. Don't forget to invite more players to join the league! <br><br>" +
            "Regards, <br>" +
            "<a href=\"http://www.utrleague.com\" target=\"_blank\">urtleague.com</a>";

}
