package com.visualisator.web

import com.visualisator.services.TodoItemService
import com.visualisator.services.dto.TodoItemDTO
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class IndexController (
        val todoItemService: TodoItemService ) {


    @GetMapping("/")
    fun enterIndexPage(model: Model): String {
        model.addAttribute("items", todoItemService.findAllToDoItems())
        return "index"
    }

    @PostMapping(
            value = [ "/todos" ])
    fun createTodoItem(itemDTO: TodoItemDTO): String {
        todoItemService.createTodoItem(itemDTO)
        return "redirect:/"
    }
}