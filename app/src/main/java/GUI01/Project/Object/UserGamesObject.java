package GUI01.Project.Object;

import GUI01.Project.GameObject;

import java.util.Date;

public class UserGamesObject extends GameObject {

    private Date purchaseDate;

    public UserGamesObject(GameObject game) {
        super(game.getId(), game.getTitle(), game.getPrice(), game.getAddedDate());
        this.purchaseDate = new Date();
    }

    public UserGamesObject(int id, String title, double price, Date date, Date purchaseDate) {
        super(id, title, price, date);
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }
}
