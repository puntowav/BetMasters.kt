

sealed class GameCategory(var isSelected:Boolean = false){
    object Filter : GameCategory()
    object Lol : GameCategory()
    object Valorant : GameCategory()
    object Csgo : GameCategory()
}