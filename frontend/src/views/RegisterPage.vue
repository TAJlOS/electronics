<template>
  <div class="register-page-wrapper">
    <div class="register-page">
      <div class="topic">Регистрация</div>
      <div v-if="isMessage" class="message">
        <p>{{ this.message }}</p>
      </div>
      <form action="/register" @submit.prevent="register" novalidate>
        <label for="username">Имя пользователя</label>
        <div class="login-area">
          <input id="username" name="username" v-model="username" />
        </div>
        <label for="password">Пароль</label>
        <div class="password-area">
          <input type="password" id="password" name="password" v-model="password" />
        </div>
        <div class="control">
          <input
            type="submit"
            class="register-button"
            value="Зарегистрироваться"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterPage",
  data() {
    return {
      username: "",
      password: "",
      message: ""
    };
  },
  created() {
    document.title = "Регистрация";
  },
  methods: {
    register() {
      if (this.password.length !== 0 && this.username.length !== 0) {
        this.$store
          .dispatch("REGISTER_REQUEST", {
            username: this.username,
            password: this.password
          })
          .then(() => {
            this.message = "Пользователь успешно зарегистрирован";
          })
          .catch(error => {
            if (error.response) {
              if (error.response.status === 409) {
                this.message = "Пользователь с таким именем уже существует";
              } else {
                this.message = "Ошибка сервера";
              }
            } else {
              this.message = "Ошибка сервера";
            }
          });
      } else {
        this.message = "Не все поля заполнены";
      }
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
.register-page-wrapper {
  width: 100%;
  height: 100%;
  font-family: PTSans, Helvetica, Arial, sans-serif;
}
.register-page {
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
  padding: 3px 7px;
}
input:focus {
  outline: none;
}
.control {
  text-align: center;
  margin-top: 30px;
}
.register-button {
  outline: none;
  background: white;
  border: 1px solid #ddd;
  margin: 10px 50px;
  height: 50px;
  width: 250px;
  border-radius: 5px;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
}

.register-button:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
.register-button:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}
</style>
