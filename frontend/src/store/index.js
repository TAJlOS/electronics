import Vue from "vue";
import Vuex from "vuex";
import Axios from "axios";

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    REST_SERVER_URL: process.env.HEROKU_HOST || "http://localhost:8080/",
    catalog: {
      content: [],
      total_pages: JSON.parse(localStorage.getItem("total_pages")) || 0,
      current_page: JSON.parse(localStorage.getItem("current_page")) || 1,
      max_pages: 5,
      filters_request: localStorage.getItem("filters_request") || "",
      order: JSON.parse(localStorage.getItem("order")) || 0
    },
    left_page: {
      parameters: [],
      parameters_state:
        JSON.parse(localStorage.getItem("parameters_state")) || {}
    },
    basket: {
      basket_list: JSON.parse(localStorage.getItem("basket_list")) || [],
      cnt_list: JSON.parse(localStorage.getItem("cnt_list")) || {},
      order_sum: JSON.parse(localStorage.getItem("order_sum")) || 0,
      order_product_cnt:
        JSON.parse(localStorage.getItem("order_product_cnt")) || 0
    },
    jwtToken: localStorage.getItem("jwtToken") || "",
    user: localStorage.getItem("user") || "",
    isAuthenticated:
      JSON.parse(localStorage.getItem("isAuthenticated")) || false,
    userOrdersList: []
  },
  getters: {
    TOTAL_PAGES: state => {
      return state.catalog.total_pages;
    },
    CURRENT_PAGE: state => {
      return state.catalog.current_page;
    },
    MAX_PAGES: state => {
      return state.catalog.max_pages;
    },
    PRODUCT_LIST: state => {
      return state.catalog.content;
    },
    FILTERS: state => {
      return state.catalog.filters_request;
    },
    PARAMETER_LIST: state => {
      return state.left_page.parameters;
    },
    BASKET_LIST: state => {
      return state.basket.basket_list;
    },
    IS_BASKET_LIST_EMPTY: state => {
      return state.basket.basket_list.length === 0;
    },
    ORDER_SUM: state => {
      return state.basket.order_sum;
    },
    ORDER_PRODUCT_CNT: state => {
      return state.basket.order_product_cnt;
    }
  },
  mutations: {
    SET_PRODUCT_LIST: (state, payload) => {
      state.catalog.content = payload;
    },
    SET_TOTAL_PAGES: (state, payload) => {
      state.catalog.total_pages = payload;
      localStorage.setItem(
        "total_pages",
        JSON.stringify(state.catalog.total_pages)
      );
    },
    NEXT_PAGE: state => {
      if (state.catalog.current_page < state.catalog.total_pages) {
        state.catalog.current_page++;
        localStorage.setItem(
          "current_page",
          JSON.stringify(state.catalog.current_page)
        );
      }
    },
    PREV_PAGE: state => {
      if (state.catalog.current_page !== 1) {
        state.catalog.current_page--;
        localStorage.setItem(
          "current_page",
          JSON.stringify(state.catalog.current_page)
        );
      }
    },
    TO_PAGE: (state, payload) => {
      state.catalog.current_page = payload;
      localStorage.setItem(
        "current_page",
        JSON.stringify(state.catalog.current_page)
      );
    },
    INIT_PARAMETER_LIST: (state, payload) => {
      for (let index in payload) {
        let param = payload[index];
        if (param.type === "input") {
          if (!state.left_page.parameters_state[param.name]) {
            state.left_page.parameters_state[param.name] = {
              cur_min: param.min,
              cur_max: param.max,
              open: false
            };
          } else {
            let cur_min = state.left_page.parameters_state[param.name].cur_min;
            state.left_page.parameters_state[param.name].cur_min = Math.max(
              cur_min,
              param.min
            );
            let cur_max = state.left_page.parameters_state[param.name].cur_max;
            state.left_page.parameters_state[param.name].cur_max = Math.min(
              cur_max,
              param.max
            );
          }
          state.left_page.parameters.push({
            name: param.name,
            type: "input",
            field: param.fieldName,
            min: param.min,
            max: param.max
          });
        }
        if (param.type === "checkbox") {
          if (!state.left_page.parameters_state[param.name]) {
            state.left_page.parameters_state[param.name] = {
              checkedList: [],
              open: false
            };
          } else {
            let values = [];
            for (let index in param.values) {
              values.push(param.values[index].name);
            }
            let checkedList =
              state.left_page.parameters_state[param.name].checkedList;
            state.left_page.parameters_state[
              param.name
            ].checkedList = checkedList.filter(x => values.includes(x));
          }
          state.left_page.parameters.push({
            name: param.name,
            type: "checkbox",
            field: param.fieldName,
            values: param.values
          });
        }
      }

      let removedProperties = [];
      for (let prop in state.left_page.parameters_state) {
        let flag = true;
        for (let index in state.left_page.parameters) {
          let param = state.left_page.parameters[index];
          if (param.name === prop) {
            flag = false;
            break;
          }
        }
        if (flag) {
          removedProperties.push(prop);
        }
      }
      for (let index in removedProperties) {
        delete state.left_page.parameters_state[removedProperties[index]];
      }

      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    SET_PARAMETER_LIST: (state, payload) => {
      for (let index1 in state.left_page.parameters) {
        let param = state.left_page.parameters[index1];
        for (let index2 in payload) {
          let data = payload[index2];
          if (data.name === param.name) {
            if (param.type === "input") {
              if (data.min != null) {
                state.left_page.parameters[index1].min = data.min;
                state.left_page.parameters_state[param.name].cur_min = data.min;
              }
              if (data.max != null) {
                state.left_page.parameters[index1].max = data.max;
                state.left_page.parameters_state[param.name].cur_max = data.max;
              }
            } else if (param.type === "checkbox") {
              state.left_page.parameters[index1].values = data.values;
            }
            break;
          }
        }
      }
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    SET_INPUT_FILTER_VALUES: (state, payload) => {
      state.left_page.parameters_state[payload.name].cur_min = payload.min;
      state.left_page.parameters_state[payload.name].cur_max = payload.max;
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    SET_CHECKBOX_FILTER_VALUES: (state, payload) => {
      state.left_page.parameters_state[payload.name].checkedList =
        payload.checkedList;
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    SET_FILTER_FLAG: (state, payload) => {
      state.left_page.parameters_state[payload.name].open = payload.flag;
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    REMOVE_VALUE_IN_CHECKED_LIST: (state, payload) => {
      const index = state.left_page.parameters_state[
        payload.name
      ].checkedList.indexOf(payload.value);
      state.left_page.parameters_state[payload.name].checkedList.splice(
        index,
        1
      );
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    ADD_VALUE_IN_CHECKED_LIST: (state, payload) => {
      state.left_page.parameters_state[payload.name].checkedList.push(
        payload.value
      );
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    RESET_FILTER_VALUES: state => {
      for (let index in state.left_page.parameters) {
        let param = state.left_page.parameters[index];
        if (param.type === "checkbox") {
          state.left_page.parameters_state[param.name].checkedList = [];
        }
      }
      localStorage.setItem(
        "parameters_state",
        JSON.stringify(state.left_page.parameters_state)
      );
    },
    SET_FILTERS_REQUEST: (state, payload) => {
      state.catalog.filters_request = payload;
      localStorage.setItem("filters_request", state.catalog.filters_request);
    },
    SET_SORTING_FLAG: (state, payload) => {
      state.catalog.order = payload;
      localStorage.setItem("order", JSON.stringify(state.catalog.order));
    },
    ADD_PRODUCT_TO_BASKET: (state, payload) => {
      state.basket.basket_list.push(payload);
      state.basket.cnt_list[payload.id] = 1;
      state.basket.order_product_cnt++;
      state.basket.order_sum += payload.price;

      localStorage.setItem(
        "basket_list",
        JSON.stringify(state.basket.basket_list)
      );
      localStorage.setItem("cnt_list", JSON.stringify(state.basket.cnt_list));
      localStorage.setItem("order_product_cnt", state.basket.order_product_cnt);
      localStorage.setItem("order_sum", state.basket.order_sum);
    },
    INC_PRODUCT_CNT: (state, payload) => {
      state.basket.cnt_list[payload.id]++;
      state.basket.order_product_cnt++;
      state.basket.order_sum += payload.price;
      localStorage.setItem("cnt_list", JSON.stringify(state.basket.cnt_list));
      localStorage.setItem("order_product_cnt", state.basket.order_product_cnt);
      localStorage.setItem("order_sum", state.basket.order_sum);
    },
    DEC_PRODUCT_CNT: (state, payload) => {
      if (state.basket.cnt_list[payload.id] === 1) {
        for (let index in state.basket.basket_list) {
          let data = state.basket.basket_list[index];
          if (data.id === payload.id) {
            state.basket.basket_list.splice(index, 1);

            localStorage.setItem(
              "basket_list",
              JSON.stringify(state.basket.basket_list)
            );

            break;
          }
        }
        delete state.basket.cnt_list[payload.id];
      } else {
        state.basket.cnt_list[payload.id]--;
      }
      state.basket.order_product_cnt--;
      state.basket.order_sum -= payload.price;

      localStorage.setItem("cnt_list", JSON.stringify(state.basket.cnt_list));
      localStorage.setItem("order_product_cnt", state.basket.order_product_cnt);
      localStorage.setItem("order_sum", state.basket.order_sum);
    },
    DEL_PRODUCT: (state, payload) => {
      for (let index in state.basket.basket_list) {
        let data = state.basket.basket_list[index];
        if (data.id === payload.id) {
          state.basket.basket_list.splice(index, 1);

          localStorage.setItem(
            "basket_list",
            JSON.stringify(state.basket.basket_list)
          );

          break;
        }
      }
      const cnt = state.basket.cnt_list[payload.id];
      state.basket.order_product_cnt -= cnt;
      state.basket.order_sum -= cnt * payload.price;
      delete state.basket.cnt_list[payload.id];

      localStorage.setItem("cnt_list", JSON.stringify(state.basket.cnt_list));
      localStorage.setItem("order_product_cnt", state.basket.order_product_cnt);
      localStorage.setItem("order_sum", state.basket.order_sum);
    },
    SET_JWT_TOKEN: (state, payload) => {
      state.jwtToken = payload;
      localStorage.setItem("jwtToken", state.jwtToken);
    },
    SET_AUTHENTICATED: (state, payload) => {
      state.isAuthenticated = payload;
      localStorage.setItem(
        "isAuthenticated",
        JSON.stringify(state.isAuthenticated)
      );
    },
    SET_USER: (state, payload) => {
      state.user = payload;
      localStorage.setItem("user", state.user);
    },
    CLEAR_BASKET: state => {
      state.basket.order_sum = 0;
      state.basket.order_product_cnt = 0;
      state.basket.cnt_list = [];
      state.basket.basket_list = [];
      localStorage.setItem(
        "basket_list",
        JSON.stringify(state.basket.basket_list)
      );
      localStorage.setItem("cnt_list", JSON.stringify(state.basket.cnt_list));
      localStorage.setItem("order_product_cnt", state.basket.order_product_cnt);
      localStorage.setItem("order_sum", state.basket.order_sum);
    },
    SET_USER_ORDER_LIST: (state, payload) => {
      state.userOrdersList = payload;
    },
    LOGOUT: state => {
      state.isAuthenticated = false;
      state.user = "";
      state.jwtToken = "";
      localStorage.setItem(
        "isAuthenticated",
        JSON.stringify(state.isAuthenticated)
      );
      localStorage.setItem("user", state.user);
      localStorage.setItem("jwtToken", state.jwtToken);
    }
  },
  actions: {
    GET_PRODUCT_LIST: async context => {
      let args =
        "?page=" +
        context.state.catalog.current_page +
        "&order=" +
        context.state.catalog.order +
        "&" +
        context.state.catalog.filters_request;
      let { data } = await Axios.get(
        context.state.REST_SERVER_URL + "api/processors/" + args
      );
      console.log(data);
      context.commit("SET_PRODUCT_LIST", data.content);
      context.commit("SET_TOTAL_PAGES", data.totalPages);
    },
    GET_PARAMETER_LIST: async context => {
      let { data } = await Axios.get(
        context.state.REST_SERVER_URL +
          "api/processors/filters/" +
          (context.state.catalog.filters_request === ""
            ? ""
            : "?" + context.state.catalog.filters_request)
      );
      context.commit("SET_PARAMETER_LIST", data);
    },
    INIT_PARAMETER_LIST: async context => {
      let { data } = await Axios.get(
        context.state.REST_SERVER_URL +
          "api/processors/filters/" +
          (context.state.catalog.filters_request === ""
            ? ""
            : "?" + context.state.catalog.filters_request)
      );
      context.commit("INIT_PARAMETER_LIST", data);
    },
    AUTH_REQUEST: async (context, payload) => {
      await Axios.post(context.state.REST_SERVER_URL + "authenticate", payload)
        .then(response => {
          context.commit("SET_JWT_TOKEN", response.data.jwttoken);
          context.commit("SET_AUTHENTICATED", true);
          context.commit("SET_USER", payload.username);
        })
        .catch(() => {
          context.commit("SET_AUTHENTICATED", false);
        });
    },
    REGISTER_REQUEST: async (context, payload) => {
      await Axios.post(context.state.REST_SERVER_URL + "register", payload);
    },
    VERIFY_TOKEN: async context => {
      await Axios.post(context.state.REST_SERVER_URL + "verify", null, {
        headers: { Authorization: "Bearer " + context.state.jwtToken }
      })
        .then(() => {
          context.commit("SET_AUTHENTICATED", true);
        })
        .catch(() => {
          context.commit("SET_AUTHENTICATED", false);
        });
    },
    CREATE_ORDER_REQUEST: async (context, payload) => {
      await Axios.post(context.state.REST_SERVER_URL + "api/order", payload, {
        headers: { Authorization: "Bearer " + context.state.jwtToken }
      });
    },
    GET_USER_ORDER_LIST: async context => {
      await Axios.post(
        context.state.REST_SERVER_URL + "api/user/orders",
        {},
        {
          headers: { Authorization: "Bearer " + context.state.jwtToken }
        }
      ).then(response => {
        console.log(response);
        context.commit("SET_AUTHENTICATED", true);
        context.commit("SET_USER_ORDER_LIST", response.data);
      });
    }
  }
});
