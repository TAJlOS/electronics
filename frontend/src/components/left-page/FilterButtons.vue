<template>
  <div class="filter-buttons">
    <div class="filter-button-apply" v-on:click="$emit('apply-parameters')">
      <a class="filter-button-name" href="#">Применить</a>
    </div>
    <div class="filter-button-reset" v-on:click="$emit('reset-parameters')">
      <a class="filter-button-name" href="#">Сбросить</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "FilterButtons"
};
</script>

<style scoped>
.filter-buttons {
  width: calc(100% - 15px);
  margin-left: 15px;
  padding-top: 10px;
}

.filter-button-apply {
  color: white;
  text-decoration: none;
  outline: none;
  border-width: 1px;
  border-style: solid none;
  border-radius: 10px;
  text-align: center;
  border-color: rgb(206, 201, 201);
  cursor: pointer;
  background: rgb(168, 13, 13);
  background: linear-gradient(
    180deg,
    rgba(168, 13, 13, 1) 0%,
    rgba(213, 45, 45, 1) 32%,
    rgba(236, 94, 94, 1) 69%
  );
  transition: 0.2s;
  margin-top: 20px;
  margin-bottom: 20px;
}

.filter-button-apply:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
.filter-button-apply:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}

.filter-button-reset {
  color: white;
  text-decoration: none;
  outline: none;
  border-width: 1px;
  border-style: solid;
  border-radius: 10px;
  text-align: center;
  background: white;
  cursor: pointer;
  border-color: rgb(206, 201, 201);
  transition: 0.2s;
}

.filter-button-reset:hover {
  background: rgb(125, 3, 3);
  background: linear-gradient(
    180deg,
    rgba(125, 3, 3, 1) 0%,
    rgba(204, 25, 25, 1) 32%,
    rgba(252, 102, 102, 1) 69%
  );
}
.filter-button-reset:active {
  background: rgb(98, 5, 5);
  background: linear-gradient(
    180deg,
    rgba(98, 5, 5, 1) 0%,
    rgba(184, 25, 25, 1) 32%,
    rgba(240, 85, 85, 1) 69%
  );
}

.filter-button-name {
  text-decoration: none;
  color: black;
}
a {
  text-decoration: none;
  color: black;
  padding: 10px 20px;
  display: block;
  box-sizing: border-box;
}
a:hover {
  color: white;
}
</style>
