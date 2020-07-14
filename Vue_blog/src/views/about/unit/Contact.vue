<template>
  <div class="extra content">
    <div class="ui three column m-padding-tb-tiny grid middle aligned center">

    </div>
    <div class="ui QQ flowing popup top left transition hidden m-padding-lrtb">
      <img src="http://cdn.shyoc.com/img/20200714101820.png" class="image ui rounded" style="width: 120px;" />
    </div>
    <div class="ui wechat flowing popup top left transition hidden m-padding-lrtb">
      <img src="http://cdn.shyoc.com/img/20200714100653.JPG" class="image ui rounded" style="width: 120px;" />
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact",
  mounted() {
    $(".github-icon").popup({
      inline: true,
      content: "去我的Github",
      position: "bottom center"
    });
    $(".qq-icon").popup({
      popup: $(".QQ.popup"),
      position: "bottom center",
      hoverable: true
    });
    $(".wechat-icon").popup({
      popup: $(".wechat.popup"),
      position: "bottom center",
      hoverable: true
    });
  },
};
</script>

<style scoped>
</style>