package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.Customer;
import entities.Product;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Customer> customerList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Product> productSold = new ArrayList<>();

        // Requesting user option: Enter as manager or customer
        while (true) {
            int path = menu("Cliente",
                            "Gerente",
                            "Sair");

            // Exiting program
            if (path == 3) {
                System.out.println("Saindo...");
                break;
            }

            /* Customer option
            if (path == 1) {

            } */

            // Manager option
            if (path == 2) {

                // Asking for the password to use manager functions
                System.out.print("Digite a senha: ");
                String password = sc.nextLine();
                while (!password.equals("admin")) {
                    System.out.print("Senha inválida, tente novamente: ");
                    password = sc.nextLine();
                }

                // Manager loop start
                while (true) {

                    // Interactive menu with different options
                    System.out.println();
                    int option = menu("Cadastrar produtos", // Ok
                                        "Cadastrar cliente", // Ok
                                        "Ver lista de clientes",
                                        "Ver lista de produtos",
                                        "Relatório de vendas",
                                        "Sair"); // Ok


                    // Only accepting valid options
                    while (option > 6 || option <= 0) {
                        System.out.print("Opção inválida. Tente novamente: ");
                        option = sc.nextInt();
                    }

                    // Exiting manager menu
                    if (option == 6) {
                        System.out.println("Saindo...");
                        break;
                    }

                    // Manager registering products
                    if (option == 1) {
                        System.out.println("------- Cadastro de Produtos -------");

                        // Requesting product name
                        System.out.print("Produto: ");
                        String name = sc.nextLine();

                        // Requesting product category
                        System.out.print("Categoria: ");
                        String category = sc.nextLine();

                        // Requesting product quantity
                        System.out.print("Quantidade: ");
                        int quantity = sc.nextInt();

                        // Requesting product cost price
                        System.out.print("Preço de custo: R$");
                        double costPrice = sc.nextDouble();

                        // Requesting product sell price
                        System.out.print("Preço para venda: R$");
                        double sellPrice = sc.nextDouble();

                        // Adding product to productList
                        productList.add(new Product(name, category, quantity, costPrice, sellPrice));

                        // Informing success
                        System.out.println("Produto cadastrado com sucesso!");
                    }

                    // Manager registering customers
                    if (option == 2) {
                        System.out.println("------- Cadastro de Clientes -------");
                        // Requesting name
                        System.out.print("Nome:  ");
                        String name = sc.nextLine();

                        // E-mail request
                        System.out.print("E-mail: ");
                        String email = sc.nextLine();

                        // Adress request (Street, Number, City, Postal Code)
                        System.out.println("Endereço: ");
                        System.out.print("Rua: ");
                        String street = sc.nextLine();
                        System.out.print("Número: ");
                        int number = sc.nextInt();
                        System.out.print("Cidade: ");
                        String city = sc.nextLine();
                        System.out.println("CEP: ");
                        int postalCode = sc.nextInt();
                        String adress = street + ", " + number + ", " + city + ", " + postalCode;

                        // Phone number request
                        System.out.println("Número de telefone: ");
                        int phoneNumber = sc.nextInt();

                        // Adding customer to customerList
                        customerList.add(new Customer(name, email, adress, phoneNumber));

                        // Informing success
                        System.out.println("Cliente cadastrado com sucesso!");
                    }

                    // Showing registered customer list
                    if (option == 3) {
                        if (customerList.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado.");
                        }
                        else {
                            System.out.println("------- Lista de Clientes Cadastrados -------");
                            for (Customer customer: customerList) {
                                System.out.println(customer);
                            }
                        }
                    }

                    // Showing registered products list
                    if (option == 4) {
                        if (productList.isEmpty()) {
                            System.out.println("Não há produtos cadastrados.");
                        }
                        else {
                            System.out.println("------- Lista de Produtos Cadastrados -------");
                            for (Product product: productList) {
                                System.out.println(product);
                            }
                        }
                    }

                    // Showing sold products list
                    if (option == 5) {
                        if (productSold.isEmpty()) {
                            System.out.println("Nenhum produto foi vendido.");
                        }
                        else {
                            System.out.println("------- Lista de Produtos Vendidos -------");
                            for (Product product: productSold)
                                System.out.println(product);
                        }
                    }

                } // End of "manager" loop
            } // End of "manager" condition
        } // End of main loop
    } // End of "main"

    /* Interactive menu method
    * It receives String options and creates a menu requesting the option chosen by the user*/
    public static int menu(String... options) {
        Scanner sc = new Scanner(System.in);
        int c = 1;

        System.out.println();
        System.out.println("------- Menu de Opções -------");
        for (String option: options) {
            System.out.println("[" + c + "] " + option);
            c++;
        }
        System.out.print("Digite sua opção: ");

        return sc.nextInt();
    }

}
