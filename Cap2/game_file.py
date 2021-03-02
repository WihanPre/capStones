import pygame
import random

pygame.init()

#Greeting and ingaing with user.
print("Good day player.")
print()

#Added a "press Enter to start" step. Seems to be good game etiquette.
input("press Enter to start")

#Setting Screen window also picked up the ("RESIZABLE" from stack overflow. https://stackoverflow.com/questions/31538506/how-do-i-maximize-the-display-screen-in-pygame)-Personal Update decided not to use "RESIZABLE"!!!
#**I picked up on how to change the background here:https://www.petercollingridge.co.uk/tutorials/pygame-physics-simulation/creating-pygame-window/#:~:text=We%20can%20change%20the%20background,flip()%20function%20is%20called.
screen_width = 1440
screen_height = 800
screen = pygame.display.set_mode((screen_width,screen_height))
background_colour = (255,90,0)                                        

#"getting" images **Images was designed, produced and sent by Frank Wessels from Kilmer & Cruise the only digital agency you need. https://kilmerandcruise.com/
player = pygame.image.load("1.png")
baddy_1 = pygame.image.load("3.png")
baddy_2 = pygame.image.load("4.png")
baddy_3 = pygame.image.load("6.png")

#assigning variables
player_height = player.get_height()
player_width = player.get_width()
monster_height = baddy_1.get_height()
monster_width = baddy_1.get_width()
monster_height = baddy_2.get_height()
monster_width = baddy_2.get_width()
monster_height = baddy_3.get_height()
monster_width = baddy_3.get_width()

print("This is the height of the player image: " +str(player_height))
print("This is the width of the player image: " +str(player_width))
print("This is the height of the monsters image: " +str(monster_height))
print("This is the width of the monsters image: " +str(monster_width))

playerXPosition = 50
playerYPosition = 50

enemyXPosition_1 =  screen_width
enemyYPosition_1 =  random.randint(0, screen_height - monster_height)
enemyXPosition_2 =  screen_width
enemyYPosition_2 =  random.randint(0, screen_height - monster_height)
enemyXPosition_3 =  screen_width
enemyYPosition_3 =  random.randint(0, screen_height - monster_height)

keyUp = False
keyDown  = False
keyForward = False
keyBack = False

#Running Looping and Iffing to run game play
while 1:

    screen.fill(background_colour)                                  #**Background Colour Change.
    screen.blit(player, (playerXPosition, playerYPosition))
    screen.blit(baddy_1, (enemyXPosition_1, enemyYPosition_1))
    screen.blit(baddy_2, (enemyXPosition_2, enemyYPosition_2))
    screen.blit(baddy_3, (enemyXPosition_3, enemyYPosition_3))
    
    pygame.display.flip()
    
    
    for event in pygame.event.get():
     
        
        if event.type == pygame.QUIT:
            pygame.quit()
            exit(0)
        
        if event.type == pygame.KEYDOWN:
        
            
            if event.key == pygame.K_UP: 
                keyUp = True
            if event.key == pygame.K_DOWN:
                keyDown = True
        
        
        if event.type == pygame.KEYUP:
        
            
            if event.key == pygame.K_UP:
                keyUp = False
            if event.key == pygame.K_DOWN:
                keyDown = False

        if event.type == pygame.KEYDOWN:

        
            if event.key == pygame.K_RIGHT:
                keyBack = True
            if event.key == pygame.K_LEFT:
                keyForward = True

        if event.type == pygame.KEYUP:

        
            if event.key == pygame.K_RIGHT:
                keyBack = False
            if event.key == pygame.K_LEFT:
                keyForward = False
     
    
    if keyUp == True:
        if playerYPosition > 0 :
            playerYPosition -= 2
    if keyDown == True:
        if playerYPosition < screen_height - player_height:
            playerYPosition += 2
    if keyForward == True:
        if playerXPosition > 0 : 
            playerXPosition -= 2
    if keyBack == True:
        if playerXPosition < screen_width - player_width:
            playerXPosition += 2
    
    
    playerBox = pygame.Rect(player.get_rect())
    
    
    playerBox.top = playerYPosition
    playerBox.left = playerXPosition
    
    
    enemyBox = pygame.Rect(baddy_1.get_rect())
    enemyBox.top = enemyYPosition_1
    enemyBox.left = enemyXPosition_1
    enemyBox_2 = pygame.Rect(baddy_2.get_rect())
    enemyBox_2.top = enemyYPosition_2
    enemyBox_2.left = enemyXPosition_2
    enemyBox_3 = pygame.Rect(baddy_3.get_rect())
    enemyBox_3.top = enemyYPosition_3
    enemyBox_3.left = enemyXPosition_3
    
    
    if playerBox.colliderect(enemyBox) or playerBox.colliderect(enemyBox_2) or playerBox.colliderect(enemyBox_3):      #Added "or"s to add additional baddies
    
        
        print("You lose!")
       
        
        pygame.quit()
        exit(0)
        
    
    if enemyXPosition_1 < 0 - monster_width and enemyXPosition_2 < 0 - monster_width and enemyXPosition_3 < 0 - monster_width:  #Added "ands"s to add additional baddies
    
        
        print("You win!")
        
        pygame.quit()
        
        exit(0)
    
 
#Setting baddies induvidual speeds
    enemyXPosition_1 -= 0.65
    enemyXPosition_2 -= 1.00
    enemyXPosition_3 -= 0.80

 
#Thanking and greeting User
print("Thank you, play again.")
  
