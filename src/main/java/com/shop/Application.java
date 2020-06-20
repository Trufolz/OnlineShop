package com.shop;


import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import com.shop.controller.BasketController;
import com.shop.controller.ProductCategoryController;
import com.shop.controller.ProductController;
import com.shop.controller.RenderingController;
import com.shop.controller.SupplierController;
import java.sql.Connection;
import java.sql.SQLException;

import spark.Request;
import spark.Response;
import spark.Route;

public class Application {

    private static Application app;
    private RenderingController renderingController;
    private BasketController basketController;
    private ProductCategoryController productCategoryController;
    private ProductController productController;
    private SupplierController supplierController;
    private Connection connection;


    private Application() {
        this.basketController = new BasketController();
        this.renderingController = new RenderingController();
        this.productCategoryController = new ProductCategoryController();
        this.productController = new ProductController();
        this.supplierController = new SupplierController();
        this.connection = null;

    }

    public static void run(String[] args) {
        System.out.println("Application starting...");
        try {
            app = new Application();
            if (args.length > 0) {
                app.connectToDb(args[0]);
            } else {
                app.connectToDb("loadDatabase");
            }
            app.routes();
        } catch (Exception e) {
            System.out.println("There was an error " + e + " when running application.");
            System.exit(0);
        }
        System.out.println("Application started successfully.");
    }

    private void connectToDb(String arg) {
        try {
            DatabaseManager.run(arg);
            connection = DatabaseManager.getDbManager().getConnection();
        } catch (SQLException e) {
            System.out.println("there was an error:" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("there was an error:" + e);
        }
    }

    public void disconnectDb() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void routes() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");

        port(8888);

        get("/", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        productController.renderProducts(productController.showAvailableProducts()),
                    "product/index");
            }

        });

        get("/find", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        productController.renderProducts(productController.showProductByName(req, res)),
                    "product/index");
            }
        });

        get("/Category/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(productController.renderProducts(
                        productCategoryController.showProductsFromCategory(req, res)),
                    "product/index");
            }
        });

        get("/Supplier/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(productController.renderProducts(
                    supplierController.productBySuppliers(req, res)),
                    "product/index");
            }
        });
        get("/cart", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(basketController.renderProducts(
                        basketController.getBasket()),
                    "product/cart");
            }
        });

        post("/cart/add", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.addToBasket(basketController.getBasket(), req));
                res.redirect("/");
                return "";
            }
        });

        post("/cart/remove", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.removeFromBasket(basketController.getBasket(), req));
                res.redirect("/cart");
                return "";
            }
        });

        post("/item/edit", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.editBasket(basketController.getBasket(), req));
                res.redirect("/cart");
                return "";
            }
        });
      
        get("/user", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/userform");
            }
        });
      
        get("/payment", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/payment");
            }
        });
        
        get("/confirmation", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/confirmation");
            }
        });
      
        get("/product/new", new Route() {
           @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "product/new");
            }
        });

        post("/product/new", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                productController.addNewProduct(req, res);
                res.redirect("/");
                return "";
            }
        });
    }

    public static Application getApp() {
        return app;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
