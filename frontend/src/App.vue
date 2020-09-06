<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin: 0;
  min-height: 100vh;
  position: relative;
  padding-bottom: 100px;
}
</style>
<script>
export default {
  name: "App",
  created() {
    this.$store.dispatch("GET_PRODUCT_LIST");
    this.$store.dispatch("INIT_PARAMETER_LIST");
    if (this.$store.state.isAuthenticated) {
      this.$store.dispatch("VERIFY_TOKEN");
    }
  }
};
</script>
