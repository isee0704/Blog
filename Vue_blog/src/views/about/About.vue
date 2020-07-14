<template>
  <div id="main" class="m-padding-tb-max m-margin-top-small">
    <div class="ui container stackable">
      <div class="ui segment basic center right aligned">
        <div class="center aligned stackable container ui side-container">
          <div id="flip_left" class="ui basic button teal circular">
            <img src="http://cdn.shyoc.com/img/20200714100946.png" alt class="circular-img" />
          </div>
          <div id="flip_right" class="ui teal basic button circular">
            <img src="http://cdn.shyoc.com/img/20200714100958.png" alt class="circular-img" />
          </div>
          <div class="segment basic center aligned ui shape cube m-cube">
            <div class="ui sides">
              <div class="active side m-shape-side container stackable ui">
                <img class="ui image" alt src="http://cdn.shyoc.com/img/20200714122726.jpg" />
              </div>
              <side aboutTitle="关于本站" :aboutContent="aboutBlog">
              </side>
              <side aboutTitle="关于作者" :aboutContent="aboutAuthor">
              </side>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { scrollEventListener } from "common/mixin";


export default {
  name: "About",
  components: {
    side: () => import("./unit/Side"),
    appreciate: () => import("./unit/Appreciate"),
    contact: () => import("./unit/Contact"),
  },
  data() {
    return {
      aboutAuthor: `<h4 style='margin-bottom: 1.5em;'>2021届学生一名，数学与计算机专业，喜欢Code，Be Better！</h4>
      练习时长尚不足两年半，
      自知码龄和许多同龄或更年轻的大佬比起来都是微不足道，
      将自己丢出去不过是沧海一粟，
      漫长的学习生涯中一直没有为自己找到专精的技能方向，
      越学越觉得自己菜，越菜越需要学。<a
        href="https://github.com/isee0704"
        target="_blank"
        style="float: right"
        class="column aligned center github-icon"
      >
        <img src="http://cdn.shyoc.com/img/20200714102151.png" alt class="ui image mini" />
      </a>`,
      aboutBlog: `
      <strong>前端开发框架：</strong>Vue.js 2.5<br>
      <strong>前端UI框架：</strong>Semantic UI 2.4<br>
      <strong>后端开发框架：</strong>SpringBoot 2.3.0<br>
      <strong>后端持久层框架：</strong>MyBatis 3.5.4<br>
      <strong>数据库：</strong>MySQL 5.7.3、Redis 3.2.1<br>
      `
    };
  },
  mounted() {
    $("#flip_right").click(function() {
      $(".ui.cube.shape")
        .shape()
        .shape("flip right");
    });
    $("#flip_left").click(function() {
      $(".ui.cube.shape")
        .shape()
        .shape("flip left");
    });
  },
  mixins: [scrollEventListener]
};
</script>

<style scoped>
@media only screen and (max-width: 800px) {
  .ui.basic.teal.button {
    border: none !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
  .circular-img {
    opacity: 0.4;
  }
  #flip_left:active {
    background-color: rgb(255, 193, 255) !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
  #flip_right:active {
    background-color: rgb(255, 193, 255) !important;
    box-shadow: none !important;
    -webkit-box-shadow: none !important;
  }
}
.side-container {
  position: relative;
}
#flip_left {
  position: absolute;
  left: 0;
  top: 50%;
  z-index: 9;
}
#flip_right {
  position: absolute;
  right: 0;
  top: 50%;
  z-index: 9;
}
#flip_left:active {
  background-color: rgb(255, 255, 255) !important;
  box-shadow: none !important;
  -webkit-box-shadow: none !important;
}
#flip_right:active {
  background-color: rgb(255, 255, 255) !important;
  box-shadow: none !important;
  -webkit-box-shadow: none !important;
}
.circular {
  border-radius: 50% !important;
  width: 4em;
  height: 4em;
  position: relative;
}
.circular-img {
  border-radius: 50% !important;
  width: 2.5em;
  height: 2.5em;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>