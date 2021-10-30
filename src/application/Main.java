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
        // Creating the scanner
        Scanner sc = new Scanner(System.in);
        // List of registered customers(Customer object)
        List<Customer> customerList = new ArrayList<>();
        // List of registered products(Product object)
        List<Product> productList = new ArrayList<>();
        // List of products sold(Product object)
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

            // Customer option
            if (path == 1) {
                while (true) {
                    int option = menu("Ver catálogo", "Comprar", "Sair");

                    // Exiting customer menu
                    if (option == 3) {
                        System.out.println("Saindo...");
                        break;
                    }

                    // Show product catalog
                    if (option == 1) {
                        if (productList.isEmpty()) {
                            System.out.println("Não há produtos para a venda.");
                        }
                        else {
                            System.out.println("------- Catálogo de Produtos -------");
                            for (Product product: productList) {
                                System.out.println(product.productCatalog());
                            }
                        }
                    }

                    // Buy products
                    if (option == 2) {
                        System.out.print("Digite o nome do produto que você deseja comprar: ");
                        String name = sc.nextLine();
                        int pos = 0;

                        // ERRO
                        for (Product product: productList) {
                            // If the name is not on the list, request again
                            while (!product.getName().equals(name)) {
                                System.out.print("O produto digitado não existe. Tente novamente: ");
                                name = sc.nextLine();
                            }

                            // Gets object position if it matches the name
                            if (product.getName().equals(name)) {
                                pos = productList.indexOf(product);
                            }
                        }

                        // Print selected product
                        System.out.println("Produto selecionado: " + productList.get(pos).getName());

                        // Product amount request
                        System.out.print("Digite a quantidade do produto desejado: ");
                        int amount = sc.nextInt();

                        // Inform error and request again if amount <= 0
                        while (amount <= 0) {
                            System.out.print("A quantidade deve ser maior ou igual a 1. Tente novamente: ");
                            amount = sc.nextInt();
                        }
                        while (amount > productList.get(pos).getQuantity()) {
                            System.out.print("Quantidade insuficiente em estoque. Tente novamente: ");
                            amount = sc.nextInt();

                        }

                        // Total value to be paid
                        double total = amount * productList.get(pos).getSellPrice();

                        System.out.println();
                        System.out.println("Produto: " + productList.get(pos).getName());
                        System.out.println("Quantidade: " + amount);
                        System.out.println("Total: U$" + total);

                        System.out.println("Selecione o método de pagamento: ");
                        int paymentOption = menu("Boleto bancário",
                                                    "Pix",
                                                    "Cartão de crédito",
                                                    "Transferência bancária",
                                                    "Sair");

                        // Exiting buying menu
                        if (paymentOption == 5) {
                            System.out.println("Saindo...");
                            break;
                        }

                        // If payment option = "Boleto bancário"
                        if (paymentOption == 1) {
                            // Requesting customer e-mail
                            String email = emailRequest();

                            // Requesting customer CPF
                            String cpf = cpfRequest();

                            // Requesting customer adress
                            String adress = adressRequest();

                            // Informing success
                            System.out.println("Pedido confirmado! O boleto bancário será enviado para o e-mail " + email);
                            System.out.println("Após a confirmação do pagamento, seu produto será separado e enviado para " + adress);
                        }

                        // If payment option = "Pix"
                        if (paymentOption == 2) {
                            // Requesting customer e-mail
                            String email = emailRequest();

                            // Requesting customer CPF
                            String cpf = cpfRequest();

                            // Requesting customer adress
                            String adress = adressRequest();

                            // Informing success
                            System.out.println("Pedido confirmado! Realize o pagamento através da chave pix: 81.877.561/0001-75 " );
                            System.out.println("Após a confirmação do pagamento, seu produto será separado e enviado para " + adress);
                        }

                        // If payment option = "Cartão de Crédito"
                        if (paymentOption == 3) {
                            // Requesting customer e-mail
                            String email = emailRequest();

                            // Requesting customer CPF
                            String cpf = cpfRequest();

                            // Requesting customer adress
                            String adress = adressRequest();

                            // Requesting credit card data
                            System.out.print("Digite o número do cartão: ");
                            String cardNumber = sc.nextLine();
                            System.out.print("Digite o número de segurança do cartão: ");
                            String cardSecurityNumber = sc.nextLine();
                            System.out.print("Digite a bandeira do cartão: ");
                            String cardIssuer = sc.nextLine();
                            System.out.print("Digite a senha do cartão: ");
                            String cardPassword = sc.nextLine();

                            // Informing success
                            System.out.println("Compra efetuada com sucesso!" );
                            System.out.println("Após a confirmação do pagamento, seu produto será separado e enviado para " + adress);
                        }

                        // If payment option = "Transferência bancária"
                        if (paymentOption == 4) {
                            // Requesting customer e-mail
                            String email = emailRequest();

                            // Requesting customer CPF
                            String cpf = cpfRequest();

                            // Requesting customer adress
                            String adress = adressRequest();

                            // Informing success
                            System.out.println("Pedido confirmado! Realize a transferência para a seguinte conta bancária: ");
                            System.out.println("Banco: Bradesco");
                            System.out.println("Agência: 0456");
                            System.out.println("Conta(Corrente): 1139656-9 ");
                            System.out.println("Após a confirmação do pagamento, seu produto será separado e enviado para " + adress);
                        }

                        // Setting new quantity value in stock
                        int newAmount = productList.get(pos).getQuantity() - amount;
                        productList.get(pos).setQuantity(newAmount);

                        // Adding product to productSold list
                        productSold.add(productList.get(pos));

                    }
                } // End of customer loop
            }// End of customer conditional

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

    /* Method to request user's adress
     * It requests the street, number, city and postal code, then adds it all
     * to a single String and returns it*/
    public static String adressRequest() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite seu endereço: ");
        System.out.print("Rua: ");
        String street = sc.nextLine();
        System.out.print("Número: ");
        int number = sc.nextInt();
        System.out.print("Cidade: ");
        String city = sc.nextLine();
        System.out.print("CEP: ");
        String postalCode = sc.nextLine();

        return street + ", " + number + ", " + city + ", " + postalCode;
    }

    /* Method to request user's e-mail
     * It requests the e-mail and returns it
     * If the e-mail does not contain "@" it will inform error
     * and request again until it is correct*/
    public static String emailRequest() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu e-mail: ");
        String email = sc.nextLine();
        while (!email.contains("@")) {
            System.out.print("E-mail inválido. Tente novamente: ");
            email = sc.nextLine();
        }
        return email;
    }

    /* Method to request user's CPF
     * It requests the CPF and returns it
     * If the CPF length is different than 11, it will inform error
     * and request again until it is correct*/
    public static String cpfRequest() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu CPF(Apenas números): ");
        String cpf = sc.nextLine();

        while (cpf.length() != 11) {
            System.out.print("Número de CPF inválido. Tente novamente(Apenas números): ");
            cpf = sc.nextLine();
        }
        return cpf;
    }
}
