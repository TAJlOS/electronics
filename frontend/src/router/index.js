import Vue from "vue";
import VueRouter from "vue-router";
import { store } from "../store";

import Catalog from "@/views/Catalog";
import Basket from "@/views/Basket";
import Order from "@/views/Order";
import LoginPage from "@/views/LoginPage";
import RegisterPage from "@/views/RegisterPage";
import NotFoundPage from "@/views/NotFoundPage";
import OrderSuccessful from "@/views/OrderSuccessful";
import OrderUnsuccessful from "@/views/OrderUnsuccessful";
import UserOrders from "@/views/UserOrders";

const ifNotAuthenticated = (to, from, next) => {
  if (!store.state.isAuthenticated) {
    next();
    return;
  }
  next("/");
};

const loadOrders = (to, from, next) => {
  if (!store.state.isAuthenticated) {
    next("/login");
  } else {
    store.dispatch("GET_USER_ORDER_LIST").then(() => {
      if (store.state.isAuthenticated) {
        next();
      } else {
        next("/login");
      }
    });
  }
};

const ifAuthenticatedAndBasketIsNotEmpty = (to, from, next) => {
  if (!store.state.isAuthenticated) {
    next("/login");
  } else {
    store.dispatch("VERIFY_TOKEN").then(() => {
      if (store.state.isAuthenticated) {
        if (store.state.basket.basket_list.length !== 0) {
          next();
        } else {
          next("/");
        }
      } else {
        next("/login");
      }
    });
  }
};

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Catalog
  },
  {
    path: "/basket",
    component: Basket
  },
  {
    path: "/order",
    component: Order,
    beforeEnter: ifAuthenticatedAndBasketIsNotEmpty
  },
  {
    path: "/profile",
    component: UserOrders,
    beforeEnter: loadOrders
  },
  { path: "/order/successful", component: OrderSuccessful },
  { path: "/order/unsuccessful", component: OrderUnsuccessful },
  { path: "/login", component: LoginPage, beforeEnter: ifNotAuthenticated },
  { path: "/register", component: RegisterPage },
  { path: "*", component: NotFoundPage }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
