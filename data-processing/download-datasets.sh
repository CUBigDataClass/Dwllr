DATASET_FILE="all-datasets.txt"
DATASET_DIR="/datasets"

mkdir "${DATASET_DIR}"

while read line; do
	if [[ ${line:0:1} == "$" ]]; then
		stripped_line=${line:1}
		file_name=${stripped_line%~*}
		download_link=${stripped_line##*~}

		echo \#\#\# Downloading $file_name from $download_link \#\#\#

		file_location=$DATASET_DIR/$file_name

		curl -o "${file_location}" "${download_link}"
	fi



done < $DATASET_FILE