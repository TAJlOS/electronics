<template>
  <div class="login-page-wrapper">
    <div class="login-page">
      <div class="topic">Войти в личный кабинет</div>
      <div v-if="isMessage" class="message">
        <p>{{ this.message }}</p>
      </div>
      <form action="/login" @submit.prevent="login" novalidate>
        <label for="username">Имя пользователя</label>
        <div class="login-area">
          <input type="text" id="username" v-model="username" />
        </div>
        <label for="password">Пароль</label>
        <div class="password-area">
          <input type="password" id="password" v-model="password" />
        </div>
        <div class="control">
          <input type="submit" class="login-button" value="Войти" />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data() {
    return {
      username: "",
      password: "",
      message: ""
    };
  },
  created() {
    document.title = "Авторизация";
  },
  methods: {
    login() {
      this.$store
        .dispatch("AUTH_REQUEST", {
          username: this.username,
          password: this.password
        })
        .then(() => {
          if (this.$store.state.isAuthenticated) {
            this.$router.go(-1);
          } else {
            this.message = "Неверное имя пользователя или пароль";
          }
        });
    }
  },
  computed: {
    isMessage() {
      return this.message !== "";
    }
  }
};
</script>

<style scoped>
.topic {
  font-size: 26px;
  text-align: left;
  padding-top: 10px;
  padding-bottom: 30px;
}
.login-page-wrapper {
  width: 100%;
  height: 100%;
  font-family: PTSans, Helvetica, Arial, sans-serif;
}
.login-page {
  background: white;
  border: 1px solid #ddd;
  border-radius: 15px;
  width: 500px;
  padding: 30px;
  box-sizing: border-box;
  margin-left: calc(50% - 250px);
  margin-top: 10%;
  display: inline-block;
}
.message {
  text-align: center;
}
.login-area {
  padding-top: 10px;
  padding-bottom: 10px;
}
.password-area {
  padding-top: 10px;
  padding-bottom: 10px;
}
input {
  border-radius: 5px;
  border: 1px solid #ddd;
  font-size: 16px;
  height: 30px;
  width: 100%;
}
input:focus {
  outline: none;
}
.control {
  text-align: center;
  margin-top: 30px;
}
.login-button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  margin: 10px 50px;
  height: 50px;
  width: 150px;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
}

.login-button:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
.login-button:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}
</style>
