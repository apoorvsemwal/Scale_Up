from itertools import cycle as cy

game_size = int(input("What size of the game you want?\n"))

# game = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
# for i in range(game_size):
#     row = []
#     for i in range(game_size):
#         row.append(0)
#     game.append(row)

game = [[0 for i in range(game_size)] for i in range(game_size)] # List Comprehension


# Reusable Code can be put in a function to use it again and again to print the game map.
# Default values are given for the parameters just in-case if someone makes an empty call.
def display_game_board(game, player_val=0, row=0, col=0, only_disp=True):
    try:
        if game[row][col] != 0:
            print("This position is already occupied")
            return game, False
        if not only_disp:
            game[row][col] = player_val

        # head = ' '
        # for idx in range(game_size):
        #     head+='  '+str(idx)
        # print(head)
        head = '   '+'  '.join([str(idx) for idx in range(game_size)]) # List Comprehension
        print(head)

        for count, row in enumerate(game):
            print(count, row)
        return game, True

    except IndexError as e:
        print('Invalid Input! Try again.', e)
        return game, False

    except Exception as e:
        print('Something went wrong! Try again.', e)
        return game, False
    # else:
    # finally:
    # raise Exception

def all_same(row):
    if row.count(row[0]) == len(row) and row[0] != 0:
        return True
    else:
        return False


def check_horizontal_win(current_game):
    for row in current_game:
        if all_same(row):
            print(f'Player {row[0]} is the winner horizontally -- !')
            return True
        else:
            return False


def check_vertical_win(current_game):
    for col in range(len(current_game)):
        check = []
        for row in current_game:
            check.append(row[col])
        if all_same(check):
            print(f'Player {check[0]} is the winner vertically | !')
            return True
        else:
            return False


def check_diagonal_win(current_game):
    diags = []
    for idx in range(len(current_game)):
        diags.append(current_game[idx][idx])
    if all_same(diags):
        print(f'Player {diags[0]} is the winner diagonally (Left to Right \\)!')
        return True

    diags = []
    # rev_idx = len(current_game) - 1
    # for idx in range(len(current_game)):
    #     diags.append(current_game[idx][rev_idx])
    #     rev_idx += 1

    # range returns a list, reversed returns an iterator
    rows = range(len(current_game))
    cols = list(reversed(range(len(current_game))))

    # for idx in rows:
    #     diags.append(current_game[idx][cols[idx]])
    # if diags.count(diags[0]) == len(diags) and diags[0] != 0:
    #     print('Winner')

    # for row, col in zip(rows, cols):
    #     diags.append(current_game[row][col])
    # if diags.count(diags[0]) == len(diags) and diags[0] != 0:
    #     print('Winner')

    # for row, col in zip(range(len(current_game)), list(reversed(range(len(current_game))))):
    #     diags.append(current_game[row][col])
    # if diags.count(diags[0]) == len(diags) and diags[0] != 0:
    #     print('Winner')

    for row, col in enumerate(reversed(range(len(current_game)))):
        diags.append(current_game[row][col])
    if all_same(diags):
        print(f'Player {diags[0]} is the winner diagonally (Right to Left / )!')
        return True
    else:
        return False

# x = display_game_board
# x()
# game[0][1] = 1
# display_game_board()
# display_game_board(player_val=6, col=2, row=10)
# display_game_board(player_val=10)

play = True

player = [1,2] #2 Player Game

col_nums = tuple([i for i in range(game_size)])
row_nums = tuple([i for i in range(game_size)])

while play:

    game, played = display_game_board(game)

    game_won = False
    cy_iterator = cy([1, 2])
    current_player = next(cy_iterator)

    while not game_won:

        print(f'\nCurrent player is: {current_player}\n')

        col_choice = int(input(f"What coloumns do you want to play? {col_nums}\n"))
        row_choice = int(input(f"What rows do you want to play? {row_nums}\n"))

        game, played = display_game_board(game,player_val=current_player,row=row_choice,col=col_choice,only_disp=False)

        if played:

            game_won = check_diagonal_win(game)

            if not game_won:
                game_won = check_horizontal_win(game)
                if not game_won:
                    game_won = check_vertical_win(game)
                    if game_won:
                        again = input('Game Over! Would you like to play again? (y/n)\n')
                        if again.lower() == 'n':
                            play = False
                        else:
                            print("Restarting")
                else:
                    again = input('Game Over! Would you like to play again? (y/n)\n')
                    if again.lower() == 'n':
                        play = False
                    else:
                        print("Restarting\n")
            else:
                again = input('Game Over! Would you like to play again? (y/n)\n')
                if again.lower() == 'n':
                    play = False
                else:
                    print("Restarting\n")

            current_player = next(cy_iterator)