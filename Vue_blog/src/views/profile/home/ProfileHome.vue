<template>
  <main-content id="main-content">
    <template #blog_list>
      <div class="ui info large message">
        <h3>Hi,</h3>
        <p>Shyoc, 欢迎登录！</p>
      </div>
      <img src="http://cdn.shyoc.com/img/20200714103158.jpg" alt="" class="ui image rounded bordered fluid">
    </template>
  </main-content>
</template>
<script>

export default {
  name: "ProfileHome",
  components: { 
    "main-content": () => import("components/blogList/MainContent")
   }
};
</script>

<style scoped>
#main-content {
    padding-top: 3em !important;
}
</style>