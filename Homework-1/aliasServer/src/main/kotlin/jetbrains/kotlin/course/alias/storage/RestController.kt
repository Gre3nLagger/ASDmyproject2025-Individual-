package jetbrains.kotlin.course.alias.storage

import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/game")
class GameController(
    private val gameSaveService: GameSaveService
) {

    @PostMapping("/save")
    fun saveGame(@RequestParam fileName: String) {
        gameSaveService.saveGame(fileName)
    }

    @GetMapping("/load")
    fun loadGame(@RequestParam fileName: String): Map<String, Any> {
        return gameSaveService.loadGame(fileName)
    }

    @GetMapping("/savedGames")
    fun getSavedGames(): List<String> {
        val folder = File("savedGames")
        return folder.list()?.toList() ?: emptyList()
    }
}
