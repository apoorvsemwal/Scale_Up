from collections import deque


def create_graph():
    return {"a": {"c": 1, "d": 6},
            "b": {"e": 1, "g2": 9},
            "c": {"s": 2, "d": 4},
            "d": {"b": 3, "g1": 6},
            "e": {"g2": 5},
            "s": {"a": 3, "b": 7},
            "g1": {"c": 2},
            "g2": {"b": 8}
            }


def pop_based_on_search(node_list, search):
    if search == "DFS":
        return node_list.pop()
    elif search == "BFS":
        return node_list.popleft()


def run_path_search(graph, src, dest, search):
    node_list = deque([src])
    parent = {}
    visited = set()
    path_found = False
    while node_list:
        node = pop_based_on_search(node_list, search)
        if node == dest:
            parent[dest] = node
            path_found = True
            break
        else:
            for neighbour in graph[node]:
                if neighbour not in visited:
                    node_list.append(neighbour)
                    parent[neighbour] = node
                if neighbour == dest:
                    parent[dest] = node
                    path_found = True
                    break
            visited.add(node)
            if path_found:
                break
    if path_found:
        print(search + " Path found from: " + src + " to " + dest)
        return parent
    print("No " + search + " Path found from: " + src + " to " + dest)
    return None


def get_path(parent_details, src, dest):
    path = [dest]
    path_string = ''
    while path:
        node = path.pop()
        path_string = node + ',' + path_string
        if node in parent_details:
            path.append(parent_details[node])
    return path_string.strip(',')


def main():
    build_path = run_path_search(create_graph(), 'g1', 'g2', 'DFS')
    if build_path is not None:
        print(get_path(build_path, 'g1', 'g2'))

    build_path = run_path_search(create_graph(), 'g1', 'g2', 'BFS')
    if build_path is not None:
        print(get_path(build_path, 'g1', 'g2'))

if __name__ == '__main__':
    main()
