import { createStore } from 'vuex'

export default createStore({
  state: {
  },
  getters: {
    GET_TOKEN:state =>{
      return sessionStorage.getItem("token");
    },
    GET_MENU_LIST:state =>{
      return JSON.parse(sessionStorage.getItem("menuList"));
    },
  },
  mutations: {
    SET_TOKEN:(state, token)=>{
      sessionStorage.setItem("token", token);
    },
    SET_MENU_LIST:(state, menuList)=>{
      sessionStorage.setItem("menuList", JSON.stringify(menuList))
    }
  },
  actions: {
  },
  modules: {
  }
})
