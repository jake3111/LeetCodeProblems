class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // sort the string and use that as the hashmap key to add to the list value.
        Map<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            // sort the string and add the original to the map's value
            char [] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray);
            List<String> currAnagrams = anagrams.get(sortedKey);
            if (currAnagrams == null) {
                currAnagrams = new LinkedList<>();
                anagrams.put(sortedKey, currAnagrams);
            }
            currAnagrams.add(str);
        }
        // the map's values should be filled and we can add them to the return list
        List<List<String>> returnList = new ArrayList<>(anagrams.keySet().size());
        for (String sortedKey : anagrams.keySet()) {
            returnList.add(anagrams.get(sortedKey));
        }
        return returnList;
    }
}