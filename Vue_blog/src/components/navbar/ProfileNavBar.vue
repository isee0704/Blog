<template>
  <div
    class="ui secondary doubling transition top fixed menu m-nav-bg m-shadow-down m-padding-tb-mini"
  >
    <h2 class="ui teal four column header item">后台管理</h2>
    <div class="ui item m-item m-mobile-hide">
      <nav-bar-item v-for="(item, index) in titles" :key="index" :to="item.to">
        <template #default>
          <i class="icon teal" :class="item.icon"></i>
          {{item.title}}
        </template>
      </nav-bar-item>

    </div>
    <right-menu @logout="logout"></right-menu>
    <!-- 手机响应 -->
    <div class="ui dropdown item right m-mobile-show">
      <i class="sidebar teal icon m-icon-center"></i>
      <div class="menu vertical">
        <nav-bar-item v-for="(item, index) in titles" :key="index" :to="item.to">
          <template #default>
            <i class="icon teal" :class="item.icon"></i>
            {{item.title}}
          </template>
        </nav-bar-item>
        <div class="item img-item">
          <img
            class="image ui avatar"
            alt
            :src="$store.state.user.avatar"
            style="width: 30px !important; height: 30px!important;"
          />
        </div>
        <div class="item">
          <i class="user circle icon teal"></i>
          PanzVor
        </div>
        <div class="item" @click="logout">
          <i class="power off icon teal"></i>
          注销
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarItem from "./mobileHide/NavBarItem";
import RightMenu from "./profile/RightMenu";

import { profileLogout } from "network/profileAjax";
import { SET_USER } from "store/mutation_types";

export default {
  name: "ProfileNavBar",
  data() {
    return {
      titles: [
        { to: "/profile/blogManage", title: "博客", icon: "home"},
        { to: "/profile/typesManage", title: "分类", icon: "idea"},
        { to: "/profile/tagsManage", title: "标签", icon: "tags"},
        { to: "/profile/linksManage", title: "友链", icon: "linkify"},
        { to: "/profile/UserView", title: "访客统计", icon: "linkify"}
      ]
    };
  },
  components: {
    NavBarItem,
    RightMenu
  },
  methods: {
    logout() {
      profileLogout()
        .then(res => {
          if (res.data.flag) {
            this.$store.commit(SET_USER, {
              user: ""
            });
            localStorage.removeItem("token");
            this.$router.push("/profile/login");
          } else {
            this.$router.push("/" + res.data.statusCode);
          }
        })
        .catch(err => {
          console.log(err);
          this.$router.push("/500");
        });
    }
  }
};
</script>

<style scoped>
.img-item {
  text-align: center !important;
}
</style>