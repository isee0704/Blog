<template>
  <!--底部footer-->
  <footer class="ui m-shadow-up vertical footer segment m-padding-tb-max m-bg">
    <div class="ui container center aligned">
      <div class="ui stackable divided center aligned equal height stackable grid">
        <div class="three wide column">
          <div class="ui link list">
            <div class="item">
              <img
                src="http://cdn.shyoc.com/img/20200713222916.png"
                alt
                class="ui rounded image"
                style="width: 140px"
              />
            </div>
          </div>
        </div>
        <div class="three wide column m-text-lined">
          <h4 class="ui header m-text-thin m-text-spaced">最新博客</h4>
          <div class="ui link list">
            <router-link
              class="item m-text-lined m-lasted"
              v-for="item in $store.state.lastBlogList"
              :key="item.id"
              v-text="item.title"
              :to="'/detail/' + item.id"
              target="_blank"
              tag="a"
              :title="item.title"
            ></router-link>
          </div>
        </div>
        <div class="three wide column">
          <h4 class="ui header m-text-thin m-text-spaced">联系我</h4>
          <div class="ui link list">
            <p
              class="item m-text-lined"
              v-text="'Email: ' + $store.state.admin.email"
            ></p>
            <p
              class="item m-text-lined"
              v-text="'QQ: ' + $store.state.admin.qq"
            ></p>
          </div>
        </div>
        <div class="seven wide column">
          <h4 class="ui header m-text-thin m-text-spaced">博客介绍</h4>
          <p class="m-text-thin m-text-spaced m-opacity-mini m-text-lined" v-text="introduce"></p>
        </div>
      </div>
      <div class="ui section divider"></div>
      <p class="m-text-thin m-text-spaced m-opacity-mini" v-html="message"></p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  data() {
    return {
      introduce:
        "基于Vue + SpringBoot开发的个人博客，博客将一直维护下去，欢迎大佬们指出bug或改进，欢迎交流！欢迎互链！",
      message: ` <img src="http://cdn.shyoc.com/img/20200714095107.png"/> 浙公网安备33020502000588 | <a style="color: rgba(0,0,0,0.87)"  href="http://www.beian.miit.gov.cn/" target="_blank"> 浙ICP备20025495号 </a>| © 2020-2021 shyoc.com
      | Email：3442929@qq.com`
    };
  }
};
</script>

<style scoped>
.m-lasted {
  width: 100% !important;
  line-height: 2em !important;
  overflow: hidden !important;
  white-space: nowrap !important;
  text-overflow: ellipsis !important;
}
</style>