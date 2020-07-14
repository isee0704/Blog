<template>
  <div class="right menu item m-mobile-hide">
    <router-link to="/profile/blogPublish" class="m-teal item" :class="{active: isPublishActive}">发布</router-link>
    <div class="ui dropdown item">
      <div class="text">
        <img
          class="image ui avatar"
          alt
          src="http://cdn.shyoc.com/img/20200713220415.jpg"
          style="width: 30px !important; height: 30px!important;"
        />
        <span class="m-teal">Shyoc</span>
      </div>
      <i class="icon teal dropdown"></i>
      <div class="menu">
        <a class="item" @click="logout">注销</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "RightMenu",
  mounted() {
    $(".ui.dropdown").dropdown({
      on: "click"
    });
  },
  methods: {
    logout() {
      this.$emit("logout");
    }
  },computed: {
    isPublishActive() {
      return this.$route.path.indexOf("/blogPublish") != -1;
    }
  }
};
</script>

<style>
</style>