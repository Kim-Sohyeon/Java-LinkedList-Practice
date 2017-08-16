import java.io.*;
import java.util.*;
 
public class CLinkedList {
	public static void main(String[] args) throws IOException {
		CLinkedListNode newNode;
		CLinkedListNode LinkHead=null;
		CLinkedListNode LinkLast=null;
 
		int index=0;
		Scanner in = new Scanner(System.in);
		
		
		try {
			BufferedReader fileHead = new BufferedReader(new FileReader("정리품목.txt"));
			
			newNode = new CLinkedListNode();
			 
			LinkHead=newNode;
			newNode.Data = fileHead.readLine();
			newNode.Prev = null;
			newNode.Next = null;
			newNode.CurPositionNo = ++index;
			System.out.print(newNode.Data);
			System.out.println("");
 
			CLinkedListNode temp = new CLinkedListNode();
			while(newNode.Data != null) {
				newNode.Next = new CLinkedListNode();
				temp = newNode;
				newNode = newNode.Next;
				newNode.Prev = temp;
				newNode.Data = fileHead.readLine();
				if(newNode.Data == null)
					break;
				newNode.CurPositionNo = ++index;
				LinkLast = newNode;
				System.out.print(newNode.Data);
				System.out.println("");
				
				fileHead.close();
				
				System.out.println("======================================");
				System.out.println("1. 맨 앞에 추가");
				System.out.println("2. 맨 뒤에 추가");
				System.out.println("3. 항목 삭제");
				System.out.println("4. 번호로 찾기");
				System.out.println("5. 내용으로 찾기");
				System.out.println("6. 전체보기");
				System.out.println("======================================");
				
				System.out.print("항목을 선택하시오.");
				int ii = in.nextInt();
				
				switch(ii) {
				case 1:
					AddFirst(LinkHead, LinkLast);
					break;
				case 2:
					AddLast(LinkHead, LinkLast, index);
					break;
				case 3:
					Remove(index, LinkHead, LinkLast);
					break;
				case 4:
					Search(index, LinkHead, LinkLast);
					break;
				case 5:
					Search2(index, LinkHead, LinkLast);
					break;
				case 6:
					ViewListData(LinkHead, LinkLast);
					break;
				}
				in.close();
			}
		} catch(Exception e) {
			System.out.println("파일을 찾을 수 없습니다.");
			return;
		}
}
	static void AddFirst(CLinkedListNode head, CLinkedListNode last) {
		System.out.println("추가할 항목을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String s = "";
		s = sc.nextLine();
		
		CLinkedListNode newNode = new CLinkedListNode();
 
		newNode.Data = s;
		newNode.Prev = null;
		newNode.Next = head;
		newNode.CurPositionNo = 1;
		
		head = newNode;
 
		CLinkedListNode reNode = new CLinkedListNode();
		CLinkedListNode temp = new CLinkedListNode();
		
		reNode = head;
		temp = head;
		
		while(reNode.Next != null) {
			reNode.CurPositionNo += 1;
			System.out.println(reNode.CurPositionNo);
			reNode = temp.Next;
			temp = reNode;
		}
		System.out.println("리스트 맨 앞에 " + s + "항목을 추가하였습니다.");
		
		temp = head;
		
		try {
			FileWriter fw = new FileWriter(new File("정리품목.txt"), false);
			while(temp != last) {
				fw.write(temp.Data + "\n");
				fw.flush();
				temp = temp.Next;
			}
			fw.write(last.Data);
			fw.flush();
			
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
	
	static void AddLast(CLinkedListNode head, CLinkedListNode last, int index) {
		System.out.println("추가할 항목을 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String s = "";
		s = sc.nextLine();
		
		CLinkedListNode newNode = new CLinkedListNode();
 
		newNode.Data = s;
		newNode.Prev = last;
		newNode.Next = null;
		newNode.CurPositionNo = ++index;
		
		last.Next = newNode;
		
		System.out.println(newNode.CurPositionNo);
		
		System.out.println("리스트 맨 마지막에 " + s + "항목을 추가하였습니다.");
		
		sc.close();
	}
	
	static void Remove(int index, CLinkedListNode head, CLinkedListNode last) {
		System.out.println("지울 항목의 번호를 입력하세요.");
		String s = null;
		Scanner sc = new Scanner(System.in);
		int targetNo = sc.nextInt();
		
		CLinkedListNode temp = new CLinkedListNode();
		temp = head;
		
		for(int i=0; i<index; i++ ) {
			if(temp.CurPositionNo == targetNo) {
				s = temp.Data;
				break;
			}
			temp = temp.Next;
		}
		
		if(temp == head) {
			temp.Next = null;
			temp.Data = "";
		}
		else if(temp == last) {
			temp.Prev = null;
			temp.Data = "";
		}
		else {
			temp.Data = "";
			temp.Prev.Next = temp.Next;
		}
		
		if(targetNo > index) {
			System.out.println("찾을 수 없습니다.");
			return;
		} else {
			System.out.println("리스트의 " + targetNo + "번째 데이터 " + s + "삭제하였습니다.");
		}
		
		temp = head;
		
		try {
			FileWriter fw = new FileWriter(new File("정리품목.txt"), false);
			while(temp != last) {
				fw.write(temp.Data + "\n");
				fw.flush();
				temp = temp.Next;
			}
			fw.write(last.Data);
			fw.flush();
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static String Search(int index, CLinkedListNode head, CLinkedListNode last) {
		System.out.println("찾을 항목의 번호를 입력하세요.");
		String s = null;
		Scanner sc = new Scanner(System.in);
		int targetNo = sc.nextInt();
		
		if(targetNo > index || targetNo <= 0) {
			System.out.println("찾는 항목이 없습니다.");
			sc.close();
			return null;
		}
		
		CLinkedListNode temp = new CLinkedListNode();
		temp = head;
		
		for(int i=0; i<index; i++ ) {
			if(temp.CurPositionNo == targetNo) {
				s = temp.Data;
				break;
			}
			temp = temp.Next;
		}
		
		if(temp == head) {
			temp.Next = null;
			temp.Data = "";
		}
		else if(temp == last) {
			temp.Prev = null;
			temp.Data = "";
		}
		else {
			temp.Data = "";
			temp.Prev.Next = temp.Next;
		}
		
		System.out.println(targetNo + "번째 인덱스의 데이터는 " + s + "입니다.");
		sc.close();
		return s;
	}

	static int Search2(int index, CLinkedListNode head, CLinkedListNode last) {
		System.out.println("찾을 항목의 내용을 입력하세요.");
		String s = null;
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		int i=0;
		
		CLinkedListNode temp = new CLinkedListNode();
		temp = head;
		
		for(i=0; i<index; i++ ) {
			if(temp.Data.equals(s)) {
				s = temp.Data;
				break;
			}
			temp = temp.Next;
		}
		
		if(i >= index) {
			System.out.println("찾는 항목이 없습니다.");
		}
		
		if (temp == head) {
			temp.Next = null;
			temp.Data = "";
		}
		else if (temp == last) {
			temp.Prev = null;
			temp.Data = "";
		}
		else {
			temp.Data = "";
			temp.Prev.Next = temp.Next;
		}
		
		System.out.println((i+1) + "번째 인덱스의 데이터는 " + s + "입니다.");
		sc.close();
		return index;
	}
	
	static void ViewListData(CLinkedListNode head, CLinkedListNode last) {
		CLinkedListNode temp = new CLinkedListNode();
		
		temp = head;
		
		while(temp != last) {
			System.out.println(temp.CurPositionNo + "." + temp.Data);
			temp = temp.Next;
		}
		if(head!=last) {
			System.out.println(last.CurPositionNo + "." + last.Data);
		}
		else {
			System.out.println("파일이 비어있습니다.");
		}
	}
}