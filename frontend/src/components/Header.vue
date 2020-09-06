<template>
  <header>
    <div class="header-title">
      <router-link to="/">Магазин Электронной Техники</router-link>
    </div>
    <div class="authorisation">
      <div
        v-if="!this.$store.state.isAuthenticated"
        class="authorisation-wrapper"
      >
        <router-link to="/login">Войти</router-link>
        <router-link to="/register">Регистрация</router-link>
      </div>
      <div v-else class="profile">
        <p @click="changeFlag">{{ this.$store.state.user }}</p>
        <div v-if="flag" class="dropdown">
          <ul class="dropdown-list">
            <li><router-link to="/profile">Заказы</router-link></li>
            <li><p @click="logout">Выход</p></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="basket">
      <div class="basket-wrapper">
        <router-link to="/basket">Корзина</router-link>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      flag: false
    };
  },
  methods: {
    changeFlag() {
      this.flag = !this.flag;
    },
    logout() {
      this.$store.commit("LOGOUT");
    }
  }
};
</script>

<style scoped>
header {
  background-color: rgb(216, 42, 42);
  display: inline-flex;
  width: 100%;
  flex-direction: column;
}
.profile {
  text-align: right;
  width: 100px;
  padding-right: 120px;
  margin-left: calc(100% - 220px);
  cursor: pointer;
}
.header-title {
  height: 50px;
  width: 100%;
  color: white;
  font-size: 20px;
  float: left;
  text-align: center;
  margin-top: 20px;
  cursor: pointer;
}
.authorisation-wrapper {
  display: inline-flex;
  flex-direction: row;
  justify-content: flex-end;
  padding-right: 100px;
  width: 100%;
  box-sizing: border-box;
}
.authorisation {
  color: white;
  display: inline-block;
  justify-content: flex-end;
  width: 100%;
}
.basket-wrapper {
  display: inline-flex;
  flex-direction: row;
  justify-content: flex-end;
}
.basket {
  text-align: right;
  padding-right: 100px;
  display: inline-block;
  width: 100px;
  margin-left: calc(100% - 200px);
}
a {
  text-decoration: none;
  color: white;
  padding: 10px 20px 10px 20px;
  display: block;
  box-sizing: border-box;
}
button a:hover {
  color: white;
}

.dropdown {
  position: absolute;
  border-radius: 5px;
  border-color: rgb(206, 201, 201);
  border-width: 1px;
  border-style: solid;
  margin-top: -5px;
  margin-left: -40px;
  display: inline-block;
  box-sizing: border-box;
  padding: 5px;
  background: white;
  z-index: 1;
}
ul {
  margin: 0;
  padding: 0 10px;
}
li {
  list-style-type: none;
  padding: 10px 10px;
}
li a {
  color: black;
  padding: 0;
  margin: 0;
  text-align: left;
  font-weight: lighter;
}
li a:hover {
  color: gray;
}
li p {
  color: black;
  padding: 0;
  margin: 0;
  text-align: left;
  font-weight: lighter;
}

li p:hover {
  color: gray;
}
</style>
