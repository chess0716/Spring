import csv
import re

def opencsv(filename):
    try:
        with open(filename, 'r', encoding='utf-8-sig') as f:
            reader = csv.reader(f)
            output = [row for row in reader]
        return output
    except UnicodeDecodeError:
        try:
            # 'utf-8-sig'로 열리지 않으면 'cp949'로 시도
            with open(filename, 'r', encoding='cp949') as f:
                reader = csv.reader(f)
                output = [row for row in reader]
            return output
        except UnicodeDecodeError as e:
            print(f"Unable to decode using 'utf-8-sig' or 'cp949'. Error: {e}")
            return None
    except FileNotFoundError:
        print(f"File '{filename}' not found.")
        return None
    except Exception as e:
        print(f"An error occurred: {e}")
        return None

def switchcsv(listName):
    if listName is not None:
        for i, row in enumerate(listName):
            for j, value in enumerate(row):
                try:
                    listName[i][j] = float(re.sub(r',', '', value))
                except ValueError:
                    pass  # 변환에 실패하면 그냥 넘어감
    return listName

def writecsv(data, foreign):
    if data is not None:
        with open(foreign, 'w', newline='', encoding='utf-8') as f:
            writer = csv.writer(f)
            writer.writerows(data)

if __name__ == "__main__":
    print("save")
    # 사용 예시
    ap = opencsv('apt_201910.csv')
    if ap is not None:
        print(ap[:3])

    # 데이터 변환
    ap = switchcsv(ap)
    if ap is not None:
        print(ap[:3])

    # 결과 저장
    writecsv(ap, 'output.csv')
    print("Saved to output.csv")


    
    
    
