import {ApolloServer} from '@apollo/server';
import {startStandaloneServer} from '@apollo/server/standalone';
import axios from 'axios';

//urls we will be using
const sandwichUrl = 'http://sandwich_ms:5007/graphql/'
const userUrl = 'http://user_ms:5005/graphql'
const shopUrl = 'http://shop_ms:5000/graphql'
const promotionUrl = 'http://promotion_ms:5003/graphql'
const orderUrl = 'http://localhost:5010/graphql'

///SCHEMA


// A schema is a collection of type definitions (hence "typeDefs")
// that together define the "shape" of queries that are executed against
// your data.
const typeDefs = `#graphql
# Comments in GraphQL strings (such as this one) start with the hash (#) symbol.

type SellingPrice{
    sellingPrice: Int
}

type Description{
    description: String
}

type Designation{
    designation: String
}

type SandwichDTO {
    id: Int!
    sellingPrice: SellingPrice!
    description: Description!
    designation: Designation!
}


# The "Query" type is special: it lists all of the available queries that
# clients can execute, along with the return type for each. In this
# case, the "books" query returns an array of zero or more Books (defined above).

type Query {
    sandwich : [SandwichDTO],
    getAllUsers : [GraphQLUsersDTO!]!
    health: String!
    listAllShops : [ShopDTO]!
    listShop(id : ID!):ShopDTO
    listAllPromotions : [GraphQLPromotionDTO!]!,
    listAllOrders : [OrderDTO]!
}

type Mutation{
    addSandwich(
        designation: String!
        sellingPrice: Int!
        description: String!
    ) : SandwichDTO
    register (
        userDTO: CreatingUserDto!
    ) : GraphQLUsersDTO
    login (
        userDTO: UserDto!
    ) : GraphQLUsersDTO
    createShop (
        shop: ShopInput!
    ) : ShopDTO
    createPromotion (
        promotion: PromotionInput!
    ) : GraphQLPromotionDTO
}

# User Microservice

input UserDto{
    email: String!
    password: String!
}

input CreatingUserDto{
    email: String!
    password: String!
    taxIdentification: String!
    username: String!
}

type GraphQLUsersDTO {
    id: ID!
    email: String!
    password: String!
    taxIdentification: String!
    username: String!
}

# Order Microservice

input OrderInput{
    shopid : Float!
    productEntries : [ProductEntry!]!
    promotion : Float!
}

type OrderDTO {
    id: ID!
    shopid : Float!
    productEntries : [ProductEntry2!]!
    price : Float!
}

type CreateOrderDTO {
    shopid : Float!
    productEntries : [ProductEntry2!]!
    promotion : Float!
}

input ProductEntry {
    sandwichPrice: Float!
    quantity: Int!
}

type ProductEntry2 {
    sandwichPrice: Float!
    quantity: Int!
}


# Shop Microservice

input ShopInput{
    mondayOpening : Int!
    mondayClosing : Int!
    tuesdayOpening : Int!
    tuesdayClosing : Int!
    wednesdayOpening : Int!
    wednesdayClosing : Int!
    thursdayOpening : Int!
    thursdayClosing : Int!
    fridayOpening : Int!
    fridayClosing : Int!
    saturdayOpening : Int!
    saturdayClosing : Int!
    sundayOpening : Int!
    sundayClosing : Int!
    name : String!
    managerName : String
    managerId : ID
}



type ShopDTO {
    id : ID!
    mondayOpening : Int!
    mondayClosing : Int!
    tuesdayOpening : Int!
    tuesdayClosing : Int!
    wednesdayOpening : Int!
    wednesdayClosing : Int!
    thursdayOpening : Int!
    thursdayClosing : Int!
    fridayOpening : Int!
    fridayClosing : Int!
    saturdayOpening : Int!
    saturdayClosing : Int!
    sundayOpening : Int!
    sundayClosing : Int!
    name : String!
    managerName : String
    managerId : ID
}

type GraphQLUsersDTO {
    id: ID!
    email: String!
    password: String!
    taxIdentification: String!
    username: String!
}

input PromotionInput{
    percentage: Float!
    from: String!
    to: String!
    shopId: ID
    promotionType: PromotionType!
}

enum PromotionType{
    GLOBAL,
    LOCAL
}

type GraphQLPromotionDTO {
    id: ID!
    percentage : Float!
    from : String!
    to : String!
    shopId : ID
    promotionType: PromotionType!
}

`;

/// SERVICES


// We have to define our interfaces to our requests and responses adapt into the data we want
interface Shop {
    id: number
    mondayOpening: number
    mondayClosing: number
    tuesdayOpening: number
    tuesdayClosing: number
    wednesdayOpening: number
    wednesdayClosing: number
    thursdayOpening: number
    thursdayClosing: number
    fridayOpening: number
    fridayClosing: number
    saturdayOpening: number
    saturdayClosing: number
    sundayOpening: number
    sundayClosing: number
    name: string
    managerName: string
    managerId: number
}

enum PromotionType {
    LOCAL,
    GLOBAL
}

interface Promotion {

    id: number
    from: string
    to: string
    percentage: number;
    type: PromotionType;
}

interface SellingPrice {
    sellingPrice: number
}

interface Description {
    description: string
}

interface Designation {
    designation: string
}

interface Sandwich {
    id: number
    sellingPrice: SellingPrice
    description: Description
    designation: Designation
}

interface User {
    id: number
    email: String
    password: String
    taxIdentification: String
    username: String
}

interface CreatingUserDto {
    email: String
    password: String
    taxIdentification: String
    username: String
}

interface UserDTO {
    email: String
    password: String
}

interface Order {
    id: number
    shopid : number
    productEntries : ProductEntry[]
    price : number
}

interface ProductEntry {
    sandwichPrice: number
    quantity: number
}

// METHODS THAT DO THE HTTP REQUESTS

// method get users
export const getAllUsers = async (): Promise<User[]> => {
    const result = await axios.post(userUrl, {
        query: `query {
        getAllUsers {
          id
          email
          password
          taxIdentification
          username
        }
      }`
    });
    return result.data.data.getAllUsers;
}

// Method register user
const register = async (arg1: String, arg2: String, arg3: String, arg4: String): Promise<User> => {

    try {
        let postSand = await axios({
            url: userUrl,
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
          mutation {
            register(userDTO:{
              email: "${arg1}"
              password: "${arg2}"
              taxIdentification: "${arg3}"
              username: "${arg4}"
            }) {
              email
              password
              taxIdentification
              username
            }
          }`,
            }
        })
        return postSand.data.data.register;
    } catch (error) {
        throw new Error(error)
    }
    ;
}

// Method login user
const login = async (arg1: String, arg2: String): Promise<User> => {

    try {
        let postSand = await axios({
            url: userUrl,
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
          mutation {
            login(userDTO:{
              email: "${arg1}"
              password: "${arg2}"
            }) {
              email
              password
              taxIdentification
              username
            }
          }`,
            }
        })
        return postSand.data.data.login;
    } catch (error) {
        throw new Error(error)
    }
    ;
}

// Method get sandwiches
export const getSandwiches = async (): Promise<Sandwich[]> => {
    const result = await axios.post(sandwichUrl, {
        query: `{
            sandwich{
                id
                sellingPrice {
                  sellingPrice
                }
                designation {
                  designation
                }
                description {
                  description
                }
            
              }
        }`
    });
    return result.data.data.sandwich;

}

// Method add sandwich
const addSandwich = async (arg1: string, arg2: number, arg3: string): Promise<Sandwich> => {

    try {
        let postSand = await axios({
            url: sandwichUrl,
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
    mutation addSandwich(
        $designation: String!
        $sellingPrice: Int!
        $description: String!
      ) {
        addSandwich(input: 
          {designation: $designation,
            sellingPrice : $sellingPrice , 
            description : $description
          })
        {
          sandwich
          {
            id
            designation{designation}
            sellingPrice{sellingPrice}
            description{description}
          }
        }
      }
    `,
                variables: {
                    designation: arg1,
                    sellingPrice: arg2,
                    description: arg3
                }
            }
        })

        return postSand.data.data.addSandwich.sandwich
    } catch (error) {
        throw new Error(error)
    }
    ;
}

//Method list all shops
const listAllShops = async (): Promise<Shop[]> => {
    try {
        let postSand = await axios({
            url: shopUrl,
            method: 'post',
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
                {
                    listAllShops {
                        id
                        mondayOpening
                        mondayClosing
                        tuesdayOpening
                        tuesdayClosing
                        wednesdayOpening
                        wednesdayClosing
                        thursdayOpening
                        thursdayClosing
                        fridayOpening
                        fridayClosing
                        saturdayOpening
                        saturdayClosing
                        sundayOpening
                        sundayClosing
                        name
                        managerName
                        managerId
                    }
                }
                `
            }

        });
        return postSand.data.data.listAllShops;
    } catch (e) {
        throw new Error(e);
    }
}

const listShop = async (id: number): Promise<Shop> => {
    try {
        let postSand = await axios({
            url: shopUrl,
            method: "post",
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
                {
                    listShop(id: ${id}) {
                        id
                        mondayOpening
                        mondayClosing
                        tuesdayOpening
                        tuesdayClosing
                        wednesdayOpening
                        wednesdayClosing
                        thursdayOpening
                        thursdayClosing
                        fridayOpening
                        fridayClosing
                        saturdayOpening
                        saturdayClosing
                        sundayOpening
                        sundayClosing
                        name
                        managerName
                        managerId
                    }
                }
                `
            }
        });
        return postSand.data.data.listShop;
    } catch (e) {
        throw new Error(e);
    }
}

const listAllPromotions = async (): Promise<Promotion> => {
    try {
        let postSand = await axios({
            url: promotionUrl,
            method: "post",
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
                {
                    listAllPromotions {
                        id
                        from
                        to
                        percentage
                        promotionType
                    }
                }
                `
            }
        });
        return postSand.data.data.listAllPromotions;
    } catch (e) {
        throw new Error(e);
    }
}

const createPromotion = async (promotion: {
    percentage: number
    from: string
    to: string
    shopId: number
    promotionType: PromotionType
}): Promise<Promotion> => {
    try {
        let postSand = await axios({
            url: promotionUrl,
            method: "post",
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `
                        mutation {
                            createPromotion(promotion: {
                                percentage: ${promotion.percentage}
                                from: "${promotion.from}"
                                to: "${promotion.to}"
                                shopId: ${promotion.shopId}
                                promotionType:${promotion.promotionType}
                            }) {
                                id
                                percentage
                                from
                                to
                                shopId
                                promotionType
                            }
                        }
                `
            }
        });
        return postSand.data.data.createPromotion;
    } catch (e) {
        throw new Error(e);
    }
}

const createShop = async (shop: {
    mondayOpening: number
    mondayClosing: number
    tuesdayOpening: number
    tuesdayClosing: number
    wednesdayOpening: number
    wednesdayClosing: number
    thursdayOpening: number
    thursdayClosing: number
    fridayOpening: number
    fridayClosing: number
    saturdayOpening: number
    saturdayClosing: number
    sundayOpening: number
    sundayClosing: number
    name: string
    managerName: string
    managerId: number
}): Promise<Shop> => {
    try {
        let postSand = await axios({
            url: shopUrl,
            method: "post",
            headers: {
                'Content-Type': 'application/json',
                // ...other headers
            },
            data: {
                query: `mutation{
                            createShop(shop: {
                                mondayOpening:${shop.mondayOpening}
                                mondayClosing:${shop.mondayClosing}
                                tuesdayOpening:${shop.tuesdayOpening}
                                tuesdayClosing:${shop.tuesdayClosing}
                                wednesdayOpening:${shop.wednesdayOpening}
                                wednesdayClosing:${shop.wednesdayClosing}
                                thursdayOpening:${shop.thursdayOpening}
                                thursdayClosing:${shop.thursdayClosing}
                                fridayOpening:${shop.fridayOpening}
                                fridayClosing:${shop.fridayClosing}
                                saturdayOpening:${shop.saturdayOpening}
                                saturdayClosing:${shop.saturdayClosing}
                                sundayOpening:${shop.sundayOpening}
                                sundayClosing:${shop.sundayClosing}
                                name:"${shop.name}"
                                managerName:"${shop.managerName}"
                                managerId: ${shop.managerId}
                            }) {
                                id
                                mondayOpening
                                mondayClosing
                                tuesdayOpening
                                tuesdayClosing
                                wednesdayOpening
                                wednesdayClosing
                                thursdayOpening
                                thursdayClosing
                                fridayOpening
                                fridayClosing
                                saturdayOpening
                                saturdayClosing
                                sundayOpening
                                sundayClosing
                                name
                                managerName
                                managerId
                            }
                        }
                `
            }
        });
        return postSand.data.data.createShop;
    } catch (e) {

    }
}


// Method get orders
//Method list all orders
export const listAllOrders = async (): Promise<Order[]> => {
    try {
        const result = await axios.post(orderUrl, {
            query: `query {
                listAllOrders {
                    id
                    shopid 
                    productEntries {
                        sandwichPrice
                        quantity
                    }
                    price 
                }
            }`
        });
        return result.data.data.listAllOrders;
    } catch (e) {
        throw new Error(e);
    }
}

/// RESOLVERS

// Resolvers define how to fetch the types defined in your schema.
const resolvers = {
    Query: {
        sandwich: async () => {
            return await getSandwiches()
        },
        getAllUsers: async () => {
            return await getAllUsers()
        },
        listAllShops: async () => {
            return await listAllShops();
        },
        listShop: async (_, args: { id: number }) => {
            return await listShop(args.id);
        },
        listAllPromotions: async () => {
            return await listAllPromotions();
        },
        listAllOrders: async () => {
            return await listAllOrders();
        }
    },
    Mutation: {
        addSandwich: async (_, args: {
            designation: string
            sellingPrice: number
            description: string
        }) => {
            return await addSandwich(args.designation, args.sellingPrice, args.description)
        },
        register: async (_, args: { userDTO: CreatingUserDto }) => {
            return await register(args.userDTO.email, args.userDTO.password,
                args.userDTO.taxIdentification, args.userDTO.username)
        },
        login: async (_, args: { userDTO: UserDTO }) => {
            return await login(args.userDTO.email, args.userDTO.password)
        },
        createShop: async (_, args: {
            shop: {
                mondayOpening: number
                mondayClosing: number
                tuesdayOpening: number
                tuesdayClosing: number
                wednesdayOpening: number
                wednesdayClosing: number
                thursdayOpening: number
                thursdayClosing: number
                fridayOpening: number
                fridayClosing: number
                saturdayOpening: number
                saturdayClosing: number
                sundayOpening: number
                sundayClosing: number
                name: string
                managerName: string
                managerId: number
            }
        }) => {
            return await createShop(args.shop);
        },
        createPromotion: async (_, args: {
            promotion: {
                percentage: number
                from: string
                to: string
                shopId: number
                promotionType: PromotionType
            }
        }) => {
            return await createPromotion(args.promotion);
        }
    }
};


// SERVER STARTER

// The ApolloServer constructor requires two parameters: your schema
// definition and your set of resolvers.
const server = new ApolloServer({
    typeDefs,
    resolvers,
});

// Passing an ApolloServer instance to the `startStandaloneServer` function:
//  1. creates an Express app
//  2. installs your ApolloServer instance as middleware
//  3. prepares your app to handle incoming requests
const {url} = await startStandaloneServer(server, {
    listen: {port: 4000},
});

console.log(`🚀  Gateway ready ready at: ${url}`);