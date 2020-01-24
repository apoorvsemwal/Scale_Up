def word_puzzle():
    print('Started')
    for i in range(365445,1000000):
      cool_num = i
      lv_str = str(i)
      lv_str_slice = lv_str[2:6]
      if(lv_str_slice == lv_str[:-5:-1]):
        i = i + 11
        lv_str = str(i)
        lv_str_slice = lv_str[1:6]
        if(lv_str_slice == lv_str[:-6:-1]):
          i = i + 110
          lv_str = str(i)
          lv_str_slice = lv_str[1:5]
          if(lv_str_slice == lv_str[-2:-6:-1]):
            i = i - 3
            lv_str = str(i)
            lv_str_slice = lv_str
            if(lv_str_slice == lv_str[::-1]):
              print(cool_num)
            else:
              continue
          else:
            continue
        else:
          continue
      else:
        continue

word_puzzle()